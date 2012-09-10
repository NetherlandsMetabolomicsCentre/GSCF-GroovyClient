package org.dbxp

import org.dbxp.GSCF

import java.util.Map
import java.util.Random
import groovyx.net.http.HTTPBuilder
import static org.junit.Assert.*
import org.junit.Test

public class GSCFTest {

	// init GSCF object
	final GSCFClient gscf = new GSCF()

	// point to running instance of GSCF
	final String URL = 'http://localhost:8080/'
	final String EndPoint = 'gscf/api'
	final String Username = 'api'
	final String Password = 'apI123!'
	final String ApiKey = '11111111-2222-3333-4444-555555555555'

	// test parameters
	static public studyToken = ''
	static public assayToken = ''
	static public templateToken = ''


	public GSCFTest(){
		gscf.setURL(URL)
		gscf.setEndPoint(EndPoint)
		gscf.setUsername(Username)
		gscf.setPassword(Password)
		gscf.setApiKey(ApiKey)
	}


	@Test public void testCreateEntities(){

		def rand  = new Random()

		// create a study without a template
		def studyJson = gscf.createEntity('Study', [title: "S-${rand.nextInt(1000000)}", description: "my description", code: "CODE${rand.nextInt(1000000)}", startDate: '01/01/1999', published: true])
		studyToken = studyJson['token']

		// create an assay without a template
		println gscf.createEntity('Assay', [module: 'Metabolomics module', name: "A-${rand.nextInt(1000000)}", studyToken: studyToken])

		// retrieve the token of the Metabolomics Assay template
		gscf.getTemplatesForEntity('Assay')['templates'].each { template ->
			if (template['name'] == 'Metabolomics assay'){
				templateToken =  template.token
			}
		}

		// create an assay with a template (with Metabolomics Assay template when found)
		def assayJson = gscf.createEntityWithTemplate('Assay', templateToken, [module: 'Metabolomics module', name: "A-T-${rand.nextInt(1000000)}", studyToken: studyToken])
		assayToken = assayJson['token']

		// create samples
		10.times { i ->
			println gscf.createEntity('Sample', [name: "SAMPLE-${i}-${rand.nextInt(1000000)}", studyToken: studyToken, assayToken: assayToken])
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
}
