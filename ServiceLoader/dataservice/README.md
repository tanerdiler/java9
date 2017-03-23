Creating JVM Image with jlink command

jdk-9/bin/jlink --modulepath jdk-9/jmods:modules --addmods data.app,data.service,data.plc --output linkeddataapp --exclude-files *.diz --compress=2
