package org.dbxp

import java.util.Map

import java.util.Random

import org.dbxp.GSCF

import groovyx.net.http.HTTPBuilder

import static org.junit.Assert.*
import org.junit.Test

public class GSCFTest {
    
	// init GSCF object    
	final GSCFClient gscf = new GSCF()
	
	// point to running instance of GSCF
	final String URL = 'http://your-gscf-instance'
	final String Username = 'username'
	final String Password = 'password'
	final String ApiKey = '4ec4730e-8d62-4c79-bc71-ed6a32698fb5'
	
	// test parameters
	final String studyToken = '5660db19-f19a-4c6c-9a51-d35cc7962a2f'
	final String assayToken = '1105f233-6566-49d0-b2cd-fe43a7c513b0'
	final String templateToken = '3d07deb4-9a39-487c-8415-bd37a25a9adb'
	
	
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
	
	@Test public void testGetFieldsForEntity(){
		
		gscf.getEntityTypes().each { entityType ->
			println gscf.getFieldsForEntity(entityType)
		}
		
		println gscf.getFieldsForEntity('Study', studyToken)
	}
	
	@Test public void testGetFieldsForEntityWithTemplate(){
		println gscf.getFieldsForEntityWithTemplate('Assay', templateToken)
	}

	@Test public void testCreateEntities(){
		
		def rand  = new Random()
		
		// create a study without a template
//		println "..fields : ${gscf.getFieldsForEntity('Study')}"
//		println 'create Study'
//		println gscf.createEntity('Study', [title: "S-${rand.nextInt(1000000)}", description: "my description", code: "CODE${rand.nextInt(1000000)}", startDate: '01/01/1999', published: true])
				
		// create an assay without a template
		println "..fields : ${gscf.getFieldsForEntity('Assay')}"
		println 'create Assay'
		println gscf.createEntity('Assay', [module: 'Metabolomics module', name: "A-${rand.nextInt(1000000)}", studyToken: studyToken])
		
		// create an assay with a template
		def assayJson = gscf.createEntityWithTemplate('Assay', templateToken, [module: 'Metabolomics module', name: "A-T-${rand.nextInt(1000000)}", studyToken: studyToken])
		println assayJson
		println assayJson['token']
		
		// create samples
		println "..fields : ${gscf.getFieldsForEntity('Sample')}"
		println 'create sample'
		3.times { i ->
			println gscf.createEntity('Sample', [name: "SAMPLE-${i}-${rand.nextInt(1000000)}", studyToken: studyToken, assayToken: assayJson['token']])
		}
		
	}
}
