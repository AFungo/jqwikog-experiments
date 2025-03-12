Import dependencies in gradle project

dependencies {
    testImplementation files('jqwikAPI-1.8.1.jar', 'jqwikENG-1.8.1.jar')
}

test {
    useJUnitPlatform{
        includeEngines 'jqwik'
    }
}
