JAXB Plugin
======
http://sourceforge.net/projects/jaxb-builder/files/?source=navbar

JAXBBuilder is an eclipse plugin for generating Java classes directly from XML schema,
XML or JSON instance files.
The plugin detects schema or instance document changes and re-generates Java code via Castor API or XJC .
It includes a JAXB project wizard,
JAXB builder and property pages for JAXB configuration.
XML to schema conversion property pages
JSON to schema conversion (via xml)

Create a JAXB project from the Java project group.
Under project properties-JAXB select your code generator either Castor of Native JAXB2.0 via XJC*
Note* If using JAXB2.0 XJC you must sepcify the full path to the XJC binary; this can be set under window-preferences-JAXB Preferences

From the resources perspective 
The project wizard creates the following directories:

schema	: place your .xsd files here.
jaxb	: the plugin will generate java source into this directory
bin	    : the plugin will place compiled source into this directory
spice	: create source files that you would like to 'spice' up the generated jaxb files (google JAXB Spice)
binding : JAXB2.0 binding files; add any binding xml files here to be included in JAXB2.0 code generation
instance: place your .xml or .json instance files here to have schema generated for them

Installation:
	Simply unzip the contents of this zip directly into the plugins/ directory of your 
eclipse installation and restart eclipse. The JAXB Wizard can be found under 'New Project/Java'
options
Note: If the wizard is not available or code generation fails with missing class files; eclipse may need to clean its plugin cache. run eclipse 
with the -clean option.