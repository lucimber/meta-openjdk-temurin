import os

from oeqa.runtime.case import OERuntimeTestCase
from oeqa.core.decorator.depends import OETestDepends
from oeqa.runtime.decorator.package import OEHasPackage

class JavaTest(OERuntimeTestCase):

    @OETestDepends(['ssh.SSHTest.test_ssh'])
    @OEHasPackage(["openjdk-8-jre", "openjdk-11-jre", "openjdk-17-jre"])
    def test_java_exists(self):
        status, output = self.target.run('which java')
        msg = 'java binary not in PATH or not on target.'
        self.assertEqual(status, 0, msg=msg)

    @OETestDepends(['java.JavaTest.test_java_exists'])
    def test_java_version(self):
        status, output = self.target.run('java -version')
        msg = 'Exit status was not 0. Output: %s' % output
        self.assertEqual(status, 0, msg=msg)
        # check java version (somehow...)
