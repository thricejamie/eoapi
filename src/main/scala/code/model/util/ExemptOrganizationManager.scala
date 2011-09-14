package code.model.util

import code.model._
import dispatch._
import java.io._
import java.util._
import java.util.zip._
import net.liftweb.actor._
import net.liftweb.http._
import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.util.Helpers._
import org.apache.http.impl.cookie.DateUtils
import scala.collection.JavaConversions._
import scala.io._

case class Refresh()

object ExemptOrganizationManager extends LiftActor {
  var refreshTime: Date = null
  
  def messageHandler = {
    case Refresh => {
      refreshTime = new Date
      Schedule.schedule(this, Refresh, 1 day)
      val endpoint = :/ ("www.irs.gov") / "pub" / "irs-soi"
      val tempFile = File.createTempFile("eofile", "tmp")
      tempFile.deleteOnExit
      var totalCount = 0
      MasterListingFileUrl.findAll.foreach(urlInfo => {
        val ep = new Request(urlInfo.url)
        val lastModified = Http(ep.HEAD >:> {_("Last-Modified").map(DateUtils.parseDate).head})
        if (lastModified != urlInfo.lastModified.is) {
          val fileOut = new FileOutputStream(tempFile)
          Http(ep >>> fileOut)
          fileOut.close
          val zipFile = new ZipFile(tempFile)
          zipFile.entries.map(entry => zipFile.getInputStream(entry)).foreach(is => Source.fromInputStream(is).getLines.foreach(e => ExemptOrganization.createFromMasterListingRawLine(e, lastModified)))
          urlInfo.lastModified(lastModified).save
        }
      })
    }
  }
}