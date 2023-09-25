import unittest
import plazma.lib.str.strlib as s

class StrlibTest(unittest.TestCase):

    def test_isEmpty(self):

        self.assertTrue(s.isEmpty(None))
        self.assertTrue(s.isEmpty(''))

        self.assertFalse(s.isEmpty(' '))
        self.assertFalse(s.isEmpty('  '))

        self.assertFalse(s.isEmpty('abc'))
        self.assertFalse(s.isEmpty(' abc'))
        self.assertFalse(s.isEmpty('abc '))
        self.assertFalse(s.isEmpty(' abc '))


    def test_isBlank(self):

        self.assertTrue(s.isBlank(None))
        self.assertTrue(s.isBlank(''))
        self.assertTrue(s.isBlank(' '))
        self.assertTrue(s.isBlank('  '))

        self.assertFalse(s.isBlank('abc'))
        self.assertFalse(s.isBlank(' abc'))
        self.assertFalse(s.isBlank('abc '))
        self.assertFalse(s.isBlank(' abc '))


    def test_size(self):

        self.assertEqual(s.size(None), 0)
        self.assertEqual(s.size(''), 0)

        self.assertEqual(s.size(' '), 1)
        self.assertEqual(s.size('  '), 2)

        self.assertEqual(s.size('abc'), 3)
        self.assertEqual(s.size(' abc'), 4)
        self.assertEqual(s.size('abc '), 4)
        self.assertEqual(s.size(' abc '), 5)

    def test_equals(self):

        self.assertFalse(s.equals(None, None))
        self.assertFalse(s.equals(None, ''))
        self.assertFalse(s.equals(None, ' '))
        self.assertFalse(s.equals(None, 'abc'))

        self.assertFalse(s.equals('', None))
        self.assertFalse(s.equals(' ', None))
        self.assertFalse(s.equals('abc', None))

        self.assertFalse(s.equals('', 'abc'))
        self.assertFalse(s.equals('abc', ''))
        self.assertFalse(s.equals(' abc', 'abc'))
        self.assertFalse(s.equals('abc ', 'abc'))
        self.assertFalse(s.equals(' abc ', 'abc'))

        self.assertTrue(s.equals('', ''))
        self.assertTrue(s.equals(' ', ' '))
        self.assertTrue(s.equals('abc', 'abc'))


    def test_normilize(self):

        self.assertIsNone(s.normalize(None))
        self.assertIsNone(s.normalize(''))
        self.assertIsNone(s.normalize(' '))

        self.assertEqual(s.normalize('abc'), 'abc')
        self.assertEqual(s.normalize(' abc'), 'abc')
        self.assertEqual(s.normalize('abc '), 'abc')
        self.assertEqual(s.normalize(' abc '), 'abc')

        self.assertEqual(s.normalize(' \t\r\t\vabc'), 'abc')
        self.assertEqual(s.normalize('abc\t\r\t\v '), 'abc')
        self.assertEqual(s.normalize(' \t\r\t\vabc\t\r\t\v '), 'abc')


    def test_trim(self):

        self.assertIsNone(s.trim(None))

        self.assertEqual(s.trim(''), '')
        self.assertEqual(s.trim('abc'), 'abc')
        self.assertEqual(s.trim(' abc'), 'abc')
        self.assertEqual(s.trim('abc '), 'abc')
        self.assertEqual(s.trim(' abc '), 'abc')

        self.assertEqual(s.trim(' \t\r\nabc'), 'abc')
        self.assertEqual(s.trim('abc \t\r\n'), 'abc')
        self.assertEqual(s.trim(' \t\r\nabc \t\r\n'), 'abc')


    def test_ltrim(self):

        self.assertIsNone(s.ltrim(None))

        self.assertEqual(s.ltrim(''), '')
        self.assertEqual(s.ltrim('abc'), 'abc')
        self.assertEqual(s.ltrim(' abc'), 'abc')
        self.assertEqual(s.ltrim('abc '), 'abc ')
        self.assertEqual(s.ltrim(' abc '), 'abc ')

        self.assertEqual(s.ltrim(' \t\r\nabc'), 'abc')
        self.assertEqual(s.ltrim('abc \t\r\n'), 'abc \t\r\n')
        self.assertEqual(s.ltrim(' \t\r\nabc \t\r\n'), 'abc \t\r\n')


    def test_rtrim(self):

        self.assertIsNone(s.ltrim(None))

        self.assertEqual(s.rtrim(''), '')
        self.assertEqual(s.rtrim('abc'), 'abc')
        self.assertEqual(s.rtrim('abc '), 'abc')
        self.assertEqual(s.rtrim(' abc'), ' abc')        
        self.assertEqual(s.rtrim(' abc '), ' abc')
        
        self.assertEqual(s.rtrim('abc \t\r\n'), 'abc')
        self.assertEqual(s.rtrim(' \t\r\nabc'), ' \t\r\nabc')
        self.assertEqual(s.rtrim(' \t\r\nabc \t\r\n'), ' \t\r\nabc')


if __name__ == '__main__':
    unittest.main()
