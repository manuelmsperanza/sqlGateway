#Generate
	mvn archetype:generate -Dfilter="org.apache.maven.archetypes:maven-archetype-j2ee-simple" -Dgroupid="com.hoffnungland.sqlGateway" -DartifactId=sqlGateway -Dpackage="com.hoffnungland.sqlGateway" -Dversion="0.0.1-SNAPSHOT"
	
#Build settings
##Add prerequisites

	<prerequisites>
		<maven>3.0.5</maven>
	</prerequisites>
###Update to java 1.8

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.source.version>1.8</java.source.version>
		<java.target.version>1.8</java.target.version>
	</properties>

# add .gitignore to mandatory empty directory

	# Ignore everything in this directory
	*
	# Except this file
	!.gitignore

# Configure the Package Clean UP Automation with GitHub Action

The Action run during the release phase of package (or you can run it manually).
Leave only the latest package version into the repository.
Create the .github/workflows/cleanupPackages.yml file.