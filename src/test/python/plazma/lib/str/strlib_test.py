import unittest
import plazma.lib.str.strlib as strlib

class StrlibTest(unittest.TestCase):

    # 1.1

    def test_isEmpty(self):

        # isEmpty(None), isEmpty(empty)
        self.assertTrue(strlib.isEmpty(None))
        self.assertTrue(strlib.isEmpty(''))

        # isEmpty(blank)
        self.assertFalse(strlib.isEmpty(' '))
        self.assertFalse(strlib.isEmpty('  '))

        # isEmpty(value)
        self.assertFalse(strlib.isEmpty('abc'))
        self.assertFalse(strlib.isEmpty(' abc'))
        self.assertFalse(strlib.isEmpty('abc '))
        self.assertFalse(strlib.isEmpty(' abc '))


    def test_isBlank(self):

        # isBlank(None), isBlank(empty)
        self.assertTrue(strlib.isBlank(None))
        self.assertTrue(strlib.isBlank(''))
        self.assertTrue(strlib.isBlank(' '))

        # isBlank(value)
        self.assertFalse(strlib.isBlank('abc'))
        self.assertFalse(strlib.isBlank(' abc'))
        self.assertFalse(strlib.isBlank('abc '))
        self.assertFalse(strlib.isBlank(' abc '))

    def test_size(self):

        # size(None), size(empty)
        self.assertEqual(0, strlib.size(None))
        self.assertEqual(0, strlib.size(''))

        # size(blank)
        self.assertEqual(1, strlib.size(' '))
        self.assertEqual(2, strlib.size('  '))

        # size(value)
        self.assertEqual(3, strlib.size('abc'))
        self.assertEqual(4, strlib.size(' abc'))
        self.assertEqual(4, strlib.size('abc '))
        self.assertEqual(5, strlib.size(' abc '))

    def test_equals(self):

        # equals(None, None)
        self.assertFalse(strlib.equals(None, None))

        # equals(None, None), equals(None, value)
        self.assertFalse(strlib.equals(None, ''))
        self.assertFalse(strlib.equals(None, ' '))
        self.assertFalse(strlib.equals(None, 'abc'))

        # equals(empty, None), equals(value, None)
        self.assertFalse(strlib.equals('', None))
        self.assertFalse(strlib.equals(' ', None))
        self.assertFalse(strlib.equals('abc', None))

        # equals(empty, value), equals(value, empty)
        self.assertFalse(strlib.equals('', 'abc'))
        self.assertFalse(strlib.equals('abc', ''))

        # equals(empty, empty), equals(blank, blank)
        self.assertTrue(strlib.equals('', ''))
        self.assertTrue(strlib.equals(' ', ' '))
        self.assertTrue(strlib.equals('  ', '  '))

        # equals(empty, blank), equals(blank, empty)
        self.assertFalse(strlib.equals('', ' '));
        self.assertFalse(strlib.equals('', '  '));
        self.assertFalse(strlib.equals(' ', ''));
        self.assertFalse(strlib.equals('  ', ''));

        # equals(value, value)
        self.assertFalse(strlib.equals(' abc', 'abc'))
        self.assertFalse(strlib.equals('abc ', 'abc'))
        self.assertFalse(strlib.equals(' abc ', 'abc'))

        self.assertTrue(strlib.equals('abc', 'abc'))
        self.assertTrue(strlib.equals(' abc', ' abc'))
        self.assertTrue(strlib.equals('abc ', 'abc '))
        self.assertTrue(strlib.equals(' abc ', ' abc '))
        
    # 1.2

    def test_normilize(self):

        # normalize(None), normalize(empty)
        self.assertIsNone(strlib.normalize(None))
        self.assertIsNone(strlib.normalize(''))

        # normalize(blank)
        self.assertIsNone(strlib.normalize(' '))
        self.assertIsNone(strlib.normalize('  '))

        # normalize(value)
        self.assertEqual('abc', strlib.normalize('abc'))
        self.assertEqual('abc', strlib.normalize(' abc'))
        self.assertEqual('abc', strlib.normalize('abc '))
        self.assertEqual('abc', strlib.normalize(' abc '))

        # normalize(' \t\n\r\f\v')
        self.assertIsNone(strlib.normalize(' \t\n\r\f\v'))
        self.assertEqual('abc', strlib.normalize(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.normalize('abc\t\n\r\f\v '))
        self.assertEqual('abc', strlib.normalize(' \t\n\r\f\vabc\t\n\r\f\v '))

    def test_normilizeBlank(self):

        # False, False
        # normalizeBlank(None, False, False), normalizeBlank(empty, False, False)
        self.assertIsNone(strlib.normalizeBlank(None, False, False));
        self.assertEqual("", strlib.normalizeBlank("", False, False));

        # normalizeBlank(blank, False, False)
        self.assertEqual(" ", strlib.normalizeBlank(" ", False, False));
        self.assertEqual("  ", strlib.normalizeBlank("  ", False, False));

        # normalizeBlank(value, False, False)
        self.assertEqual("abc", strlib.normalizeBlank("abc", False, False));
        self.assertEqual(" abc", strlib.normalizeBlank(" abc", False, False));
        self.assertEqual("abc ", strlib.normalizeBlank("abc ", False, False));
        self.assertEqual(" abc ", strlib.normalizeBlank(" abc ", False, False));

        # False, True
        # normalizeBlank(None, False, True), normalizeBlank(empty, False, True)
        self.assertIsNone(strlib.normalizeBlank(None, False, True));
        self.assertIsNone(strlib.normalizeBlank("", False, True));

        # normalizeBlank(blank, False, True)
        self.assertIsNone(strlib.normalizeBlank(" ", False, True));
        self.assertIsNone(strlib.normalizeBlank("  ", False, True));

        # normalizeBlank(value, False, True)
        self.assertEqual("abc", strlib.normalizeBlank("abc", False, True));
        self.assertEqual(" abc", strlib.normalizeBlank(" abc", False, True));
        self.assertEqual("abc ", strlib.normalizeBlank("abc ", False, True));
        self.assertEqual(" abc ", strlib.normalizeBlank(" abc ", False, True));

        # True, True
        # normalizeBlank(None, True, True), normalizeBlank(empty, True, True)
        self.assertIsNone(strlib.normalizeBlank(None, True, True));
        self.assertIsNone(strlib.normalizeBlank("", True, True));

        # normalizeBlank(blank, True, True)
        self.assertIsNone(strlib.normalizeBlank(" ", True, True));
        self.assertIsNone(strlib.normalizeBlank("  ", True, True));

        # normalizeBlank(value, True, True)
        self.assertEqual("abc", strlib.normalizeBlank("abc", True, True));
        self.assertEqual("abc", strlib.normalizeBlank(" abc", True, True));
        self.assertEqual("abc", strlib.normalizeBlank("abc ", True, True));
        self.assertEqual("abc", strlib.normalizeBlank(" abc ", True, True));

        # True, False
        # normalizeBlank(None, True, False), normalizeBlank(empty, True, False)
        self.assertIsNone(strlib.normalizeBlank(None, True, False));
        self.assertIsNone(strlib.normalizeBlank("", True, False));

        # normalizeBlank(blank, true, false)
        self.assertIsNone(strlib.normalizeBlank(" ", True, False));
        self.assertIsNone(strlib.normalizeBlank("  ", True, False));

        # normalizeBlank(value, true, false)
        self.assertEqual("abc", strlib.normalizeBlank("abc", True, False));
        self.assertEqual("abc", strlib.normalizeBlank(" abc", True, False));
        self.assertEqual("abc", strlib.normalizeBlank("abc ", True, False));
        self.assertEqual("abc", strlib.normalizeBlank(" abc ", True, False));

    # 1.3

    def test_trim(self):

        # trim(None), trim(empty)
        self.assertIsNone(strlib.trim(None))        
        self.assertEqual('', strlib.trim(''))

        # trim(blank)
        self.assertEqual('', strlib.trim(' '))
        self.assertEqual('', strlib.trim('  '))
        
        # trim(value)
        self.assertEqual('abc', strlib.trim('abc'))
        self.assertEqual('abc', strlib.trim(' abc'))
        self.assertEqual('abc', strlib.trim('abc '))
        self.assertEqual('abc', strlib.trim(' abc '))

        # trim(' \t\n\r\f\v')
        self.assertEqual('', strlib.trim(' \t\n\r\f\v'))
        self.assertEqual('abc', strlib.trim(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.trim('abc \t\n\r\f\v'))
        self.assertEqual('abc', strlib.trim(' \t\n\r\f\vabc \t\n\r\f\v'))


    def test_ltrim(self):

        # ltrim(None), ltrim(empty)
        self.assertIsNone(strlib.ltrim(None))
        self.assertEqual('', strlib.ltrim(''))

        # ltrim(blank)
        self.assertEqual('', strlib.ltrim(' '))
        self.assertEqual('', strlib.ltrim('  '))

        # trim(value)
        self.assertEqual('abc', strlib.ltrim('abc'))
        self.assertEqual('abc', strlib.ltrim(' abc'))
        self.assertEqual('abc ', strlib.ltrim('abc '))
        self.assertEqual('abc ', strlib.ltrim(' abc '))

        # trim(' \t\n\r\f\v')
        self.assertEqual('', strlib.ltrim(' \t\n\r\f\v'))
        self.assertEqual('abc', strlib.ltrim(' \t\n\r\f\vabc'))
        self.assertEqual('abc \t\n\r\f\v', strlib.ltrim('abc \t\n\r\f\v'))
        self.assertEqual('abc \t\n\r\f\v', strlib.ltrim(' \t\n\r\f\vabc \t\n\r\f\v'))

    def test_rtrim(self):

        # rtrim(None), rtrim(empty)
        self.assertIsNone(strlib.rtrim(None))
        self.assertEqual('', strlib.rtrim(''))

        # rtrim(blank)
        self.assertEqual('', strlib.rtrim(' '))
        self.assertEqual('', strlib.rtrim('  '))

        # trim(value)
        self.assertEqual('abc', strlib.rtrim('abc'))
        self.assertEqual(' abc', strlib.rtrim(' abc'))
        self.assertEqual('abc', strlib.rtrim('abc '))
        self.assertEqual(' abc', strlib.rtrim(' abc '))
        
        # trim(' \t\n\r\f\v')
        self.assertEqual('', strlib.rtrim(' \t\n\r\f\v'))
        self.assertEqual(' \t\n\r\f\vabc', strlib.rtrim(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.rtrim('abc \t\n\r\f\v'))
        self.assertEqual(' \t\n\r\f\vabc', strlib.rtrim(' \t\n\r\f\vabc \t\n\r\f\v'))


if __name__ == '__main__':
    unittest.main()
