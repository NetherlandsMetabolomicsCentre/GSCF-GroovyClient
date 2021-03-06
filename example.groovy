/**
 * Example how to interface with the GSCF Api using Groovy
 *
 * Api docs	: http://studies.dbnp.org/api
 * Usage	: groovy -classpath $CLASSPATH:./org/dbxp/ example.groovy
 *
 *
 * Copyright 2012 Jeroen Wesbeek <work@osx.eu>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// requirements
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.2')

// import the Groovy Client for GSCF
import org.dbxp.GSCF

// instantiate Client
def gscf = new GSCF();

// set credentials and api key
gscf.url	= "http://old.studies.dbnp.org"		// the url of the GSCF instance
gscf.username	= '...'					// your GSCF username (with role 'client')
gscf.password	= '...'					// your GSCF password
gscf.apiKey	= '...'					// your GSCF Api Key

// fetch studies
def studies	= gscf.getStudies();

// show study count
println "${studies.count} readable studies found"
