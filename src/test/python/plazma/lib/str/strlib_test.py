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
        self.assertEqual(None, strlib.normalize(' \t\n\r\f\v'))
        self.assertEqual('abc', strlib.normalize(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.normalize('abc\t\n\r\f\v '))
        self.assertEqual('abc', strlib.normalize(' \t\n\r\f\vabc\t\n\r\f\v '))

    # 1.2

    def test_trim(self):

        self.assertIsNone(strlib.trim(None))

        self.assertEqual(strlib.trim(''), '')
        self.assertEqual(strlib.trim('abc'), 'abc')
        self.assertEqual(strlib.trim(' abc'), 'abc')
        self.assertEqual(strlib.trim('abc '), 'abc')
        self.assertEqual(strlib.trim(' abc '), 'abc')

        self.assertEqual(strlib.trim(' \t\r\nabc'), 'abc')
        self.assertEqual(strlib.trim('abc \t\r\n'), 'abc')
        self.assertEqual(strlib.trim(' \t\r\nabc \t\r\n'), 'abc')


    def test_ltrim(self):

        self.assertIsNone(strlib.ltrim(None))

        self.assertEqual(strlib.ltrim(''), '')
        self.assertEqual(strlib.ltrim('abc'), 'abc')
        self.assertEqual(strlib.ltrim(' abc'), 'abc')
        self.assertEqual(strlib.ltrim('abc '), 'abc ')
        self.assertEqual(strlib.ltrim(' abc '), 'abc ')

        self.assertEqual(strlib.ltrim(' \t\r\nabc'), 'abc')
        self.assertEqual(strlib.ltrim('abc \t\r\n'), 'abc \t\r\n')
        self.assertEqual(strlib.ltrim(' \t\r\nabc \t\r\n'), 'abc \t\r\n')


    def test_rtrim(self):

        self.assertIsNone(strlib.ltrim(None))

        self.assertEqual(strlib.rtrim(''), '')
        self.assertEqual(strlib.rtrim('abc'), 'abc')
        self.assertEqual(strlib.rtrim('abc '), 'abc')
        self.assertEqual(strlib.rtrim(' abc'), ' abc')        
        self.assertEqual(strlib.rtrim(' abc '), ' abc')
        
        self.assertEqual(strlib.rtrim('abc \t\r\n'), 'abc')
        self.assertEqual(strlib.rtrim(' \t\r\nabc'), ' \t\r\nabc')
        self.assertEqual(strlib.rtrim(' \t\r\nabc \t\r\n'), ' \t\r\nabc')


if __name__ == '__main__':
    unittest.main()
