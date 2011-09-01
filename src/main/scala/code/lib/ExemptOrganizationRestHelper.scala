package code
package lib

import model._

import net.liftweb._
import common._
import http._
import rest._
import scala.xml._
import mapper._
import json.JsonDSL._
import json.JsonAST._
import json.Xml

object ExemptOrganizationRestHelper extends RestHelper {
  implicit def string2Long(s: String): Long = augmentString(s).toLong
  implicit def toXml(eo: ExemptOrganization): Node = Xml.toXml(eo).head
  implicit def toJSON(eo: ExemptOrganization): JValue = eo.toJSON
  //implicit def toXml(eos: List[Node]): Node = <exemptOrganizations>{eos}</exemptOrganizations>
  //implicit def toXml(eos: List[ExemptOrganization]): Node = <exemptOrganizations>{eos.map(toXml)}</exemptOrganizations>
  //implicit def toJSON(eos: List[ExemptOrganization]): JValue = ("exemptOrganizations" -> eos.map(toJSON))
  
  serve {
    case "api" :: "ein" :: ein :: Nil JsonGet _ =>
      for {
        org <- ExemptOrganization.find(By(ExemptOrganization.ein, ein)) ?~ "Organization not found"
      } yield org: JValue
    case "api" :: "ein" :: ein :: Nil XmlGet _ =>
      for {
        org <- ExemptOrganization.find(By(ExemptOrganization.ein, ein)) ?~ "Organization not found"
      } yield org: Node
    /*case "api" :: "name" :: name :: Nil JsonGet _ =>
      for {
        orgs <- ExemptOrganization.findAll(Like(ExemptOrganization.name, name))
      } yield orgs: JValue*/
    /*case "api" :: "name" :: name :: Nil XmlGet _ =>
      for {
        orgs <- ExemptOrganization.findAll(Like(ExemptOrganization.name, name))
      } yield orgs: Node*/
  }
}