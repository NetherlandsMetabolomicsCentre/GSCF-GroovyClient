package org.dbxp

import org.dbxp.GSCF

import groovyx.net.http.HTTPBuilder

import static org.junit.Assert.*
import org.junit.Test

public class GSCFTest {
        
	final GSCFClient gscf = new GSCF()
	
	public GSCFTest(){

		gscf.setURL('...')
		gscf.setUsername('...')
		gscf.setPassword('...')
		gscf.setApiKey('...')
				
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

}
