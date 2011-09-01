package code
package model

import net.liftweb.common._
import net.liftweb.mapper._

class MasterListingFileUrl extends LongKeyedMapper[MasterListingFileUrl] with IdPK {
  def getSingleton = MasterListingFileUrl
  
  object url extends MappedString(this, 50)
  object lastModified extends MappedDate(this)
}

object MasterListingFileUrl extends MasterListingFileUrl with LongKeyedMetaMapper[MasterListingFileUrl] {
  override def dbAddTable = {
    (1 to 4).foreach(i => {
      MasterListingFileUrl.create.url("http://www.irs.gov/pub/irs-soi/eo" + i + ".exe").save
    })
    Empty
  }
}