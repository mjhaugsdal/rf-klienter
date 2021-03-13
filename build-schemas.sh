#!/bin/bash

cd xml-schemas-parent/ && mvn clean install && cd ..
cd kith/ && mvn clean install && cd ..
cd felleskomponent1/ && mvn clean install && cd ..
cd er-m9na1-2016-06-06/ && mvn clean install && cd ..
cd er-m9na2-2016-10-26/ && mvn clean install && cd ..
cd er-m9na3-2016-06-06/ && mvn clean install && cd ..
cd er-m9na4-2016-06-06/ && mvn clean install && cd ..
cd apprec-2004-11-21/ && mvn clean install && cd ..
cd msghead-2006-05-24/ && mvn clean install && cd ..