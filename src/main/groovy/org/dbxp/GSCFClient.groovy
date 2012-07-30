package org.dbxp

public interface GSCFClient {
	
	def getStudies()
	def getSubjectsForStudy(String studyToken)
	def getAssaysForStudy(String studyToken)
	def getEventGroupsForStudy(String studyToken)
	def getEventsForStudy(String studyToken)
	def getSamplingEventsForStudy(String studyToken)
	def getSamplesForAssay(String assayToken)
	def getMeasurementDataForAssay(String assayToken)
	def getModules()
	def getEntityTypes()
	def getTemplatesForEntity(String entityType)
}
