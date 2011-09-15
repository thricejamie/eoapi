package code
package model

import net.liftweb.json._
import net.liftweb.mapper._
import java.util.Date

class ExemptOrganization extends LongKeyedMapper[ExemptOrganization] with IdPK {
  def getSingleton = ExemptOrganization
  
  object ein extends MappedLong(this) {
    override def dbIndexed_? = true
  }
  object name extends MappedString(this, 70) {
    override def dbIndexed_? = true
  }
  object inCareOfName extends MappedString(this, 35)
  object address extends MappedString(this, 35)
  object city extends MappedString(this, 22)
  object state extends MappedString(this, 2)
  object zipCode extends MappedString(this, 10)
  object groupExemptionNumber extends MappedString(this, 4)
  object subsectionCode extends MappedString(this, 2)
  object affiliationCode extends MappedString(this, 1)
  object classificationCodes extends MappedString(this, 4) 
  object rulingDate extends MappedString(this, 6)
  object deductabilityCode extends MappedString(this, 1)
  object foundationCode extends MappedString(this, 2)
  object activityCodes extends MappedString(this, 9)
  object organizationCode extends MappedString(this, 1)
  object exemptOrgStatusCode extends MappedString(this, 2)
  object taxPeriod extends MappedString(this, 6)
  object assetCode extends MappedString(this, 1)
  object incomeCode extends MappedString(this, 1)
  object filingRequirementCode extends MappedString(this, 3)
  object accountingPeriod extends MappedString(this, 2)
  object assetAmount extends MappedString(this, 13)
  object incomeAmount extends MappedString(this, 14)
  object form990RevenueAmount extends MappedString(this, 14)
  object nteeCode extends MappedString(this, 4)
  object sortName extends MappedString(this, 35)
  object masterFileDate extends MappedDateTime(this)
  
  def toJSON: JValue = {
    import JsonDSL._
    import JsonAST._
    ("exemptOrganization" ->
      ("ein" -> ein.is) ~
      ("name" -> name.is) ~
      ("inCareOfName" -> inCareOfName.is) ~
      ("address" -> address.is) ~
      ("city" -> city.is) ~
      ("state" -> state.is) ~
      ("zipCode" -> zipCode.is) ~
      ("groupExemptionNumber" -> groupExemptionNumber.is) ~
      ("subsectionCode" -> subsectionCode.is) ~
      ("affiliationCode" -> affiliationCode.is) ~
      ("classificationCodes" -> classificationCodes.is) ~
      ("rulingDate" -> rulingDate.is) ~
      ("deductabilityCode" -> deductabilityCode.is) ~
      ("foundationCode" -> foundationCode.is) ~
      ("activityCodes" -> activityCodes.is) ~
      ("organizationCode" -> organizationCode.is) ~
      ("exemptOrgStatusCode" -> exemptOrgStatusCode.is) ~
      ("taxPeriod" -> taxPeriod.is) ~
      ("assetCode" -> assetCode.is) ~
      ("incomeCode" -> incomeCode.is) ~
      ("filingRequirementCode" -> filingRequirementCode.is) ~
      ("accountingPeriod" -> accountingPeriod.is) ~
      ("assetAmount" -> assetAmount.is) ~
      ("incomeAmount" -> incomeAmount.is) ~
      ("form990RevenueAmount" -> form990RevenueAmount.is) ~
      ("nteeCode" -> nteeCode.is) ~
      ("sortName" -> sortName.is) ~
      ("masterFileDate" -> masterFileDate.is.toString)
    )
  }
}

object ExemptOrganization extends ExemptOrganization with LongKeyedMetaMapper[ExemptOrganization] {
  def createFromMasterListingRawLine(eoStr: String, masterFileDate: Date) {
    ExemptOrganization.find(By(ExemptOrganization.ein, ein)).getOrElse(ExemptOrganization.create)
        .ein(eoStr.substring(0,9).toLong)
        .name(eoStr.substring(9,79).trim)
        .inCareOfName(eoStr.substring(79,114).trim)
        .address(eoStr.substring(114,149).trim)
        .city(eoStr.substring(149,171).trim)
        .state(eoStr.substring(171,173))
        .zipCode(eoStr.substring(173,183))
        .groupExemptionNumber(eoStr.substring(183,187).trim)
        .subsectionCode(eoStr.substring(187,189).trim)
        .affiliationCode(eoStr.substring(189,190).trim)
        .classificationCodes(eoStr.substring(190,194).trim)
        .rulingDate(eoStr.substring(194,200).trim)
        .deductabilityCode(eoStr.substring(200,201).trim)
        .foundationCode(eoStr.substring(201,203).trim)
        .activityCodes(eoStr.substring(203,212).trim)
        .organizationCode(eoStr.substring(212,213).trim)
        .exemptOrgStatusCode(eoStr.substring(213,215).trim)
        .taxPeriod(eoStr.substring(221,227).trim)
        .assetCode(eoStr.substring(227,228).trim)
        .incomeCode(eoStr.substring(228,229).trim)
        .filingRequirementCode(eoStr.substring(229,232).trim)
        .accountingPeriod(eoStr.substring(235,237).trim)
        .assetAmount(eoStr.substring(237,250).trim)
        .incomeAmount(eoStr.substring(263,264).trim + eoStr.substring(250,263).trim)
        .form990RevenueAmount(eoStr.substring(277,278).trim + eoStr.substring(264,277).trim)    
        .nteeCode(eoStr.substring(278,282).trim)
        .sortName(eoStr.substring(282).trim)
        .masterFileDate(masterFileDate)
        .save
  }
}