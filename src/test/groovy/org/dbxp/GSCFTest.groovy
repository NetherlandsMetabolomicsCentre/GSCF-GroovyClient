package org.dbxp

import org.dbxp.GSCF

import groovyx.net.http.HTTPBuilder

import static org.junit.Assert.*
import org.junit.Test

public class GSCFTest {
    
	// init GSCF object    
	final GSCFClient gscf = new GSCF()
	
	// point to running instance of GSCF
	final String URL = 'http://your.instance.of.gscf'
	final String Username = 'username'
	final String Password = 'password'
	final String ApiKey = 'api-key-of-the-user-connection'
	
	// test parameters
	final String studyToken = 'an-existing-study-token'
	final String assayToken = 'an-existing-assay-token'
	
	
	public GSCFTest(){

		gscf.setURL(URL)
		gscf.setUsername(Username)
		gscf.setPassword(Password)
		gscf.setApiKey(ApiKey)			
	}
	
	//dump some debug output to the standard output
	@Test public void testDebug() {
		println "Number of studies found: ${gscf.getStudies().count}"
		
		gscf.getStudies().studies.each { study ->
			println "${study}"
		}
	}
	
	@Test public void testGetStudiesSize() {
		assertEquals(gscf.getStudies().studies.size(), gscf.getStudies().count)		
	}
	
	@Test public void testGetStudiesClass() {
		assertEquals('class net.sf.json.JSONObject', gscf.getStudies().getClass().toString())
	}
	
	@Test public void testGetSubjectsForStudy() {
		println gscf.getSubjectsForStudy(studyToken)
	}
	
	@Test public void testGetAssaysForStudy(){
		println gscf.getAssaysForStudy(studyToken)
	}
	
	@Test public void testGetEventGroupsForStudy(){
		println gscf.getEventGroupsForStudy(studyToken)
	}
	
	@Test public void testGetEventsForStudy(){
		println gscf.getEventsForStudy(studyToken)
	}
	
	@Test public void testGetSamplingEventsForStudy(){
		println gscf.getSamplingEventsForStudy(studyToken)
	}
	
	@Test public void testGetSamplesForAssay(){
		println gscf.getSamplesForAssay(assayToken)
	}
	
	@Test public void testGetMeasurementDataForAssay(){
		println gscf.getMeasurementDataForAssay(assayToken)
	}
	
	@Test public void testGetModules(){
		println gscf.getModules()
	}
	
	@Test public void testGetEntityTypes(){
		println gscf.getEntityTypes()
	}
	
	@Test public void testGetTemplatesForEntity(){
		
		gscf.getEntityTypes().each { entityType ->
			println gscf.getTemplatesForEntity(entityType)
		}
	}
}
