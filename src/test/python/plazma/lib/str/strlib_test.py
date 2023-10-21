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
        self.assertTrue(strlib.equals(None, None))

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
        self.assertFalse(strlib.equals('', ' '))
        self.assertFalse(strlib.equals('', '  '))
        self.assertFalse(strlib.equals(' ', ''))
        self.assertFalse(strlib.equals('  ', ''))

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

    def test_normilizeSafe(self):

        # normalizeSafe(None), normalizeSafe(empty)
        self.assertEqual('', strlib.normalizeSafe(None))
        self.assertEqual('', strlib.normalizeSafe(''))

        # normalizeSafe(blank)
        self.assertEqual('', strlib.normalizeSafe(' '))
        self.assertEqual('', strlib.normalizeSafe('  '))

        # normalizeSafe(value)
        self.assertEqual('abc', strlib.normalizeSafe('abc'))
        self.assertEqual('abc', strlib.normalizeSafe(' abc'))
        self.assertEqual('abc', strlib.normalizeSafe('abc '))
        self.assertEqual('abc', strlib.normalizeSafe(' abc '))

        # normalizeSafe(' \t\n\r\f\v')
        self.assertEqual('', strlib.normalizeSafe(' \t\n\r\f\v'))
        self.assertEqual('abc', strlib.normalizeSafe(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.normalizeSafe('abc\t\n\r\f\v '))
        self.assertEqual('abc', strlib.normalizeSafe(' \t\n\r\f\vabc\t\n\r\f\v '))

    def test_normilizeBlank(self):

        # False, False
        # normalizeBlank(None, False, False), normalizeBlank(empty, False, False)
        self.assertIsNone(strlib.normalizeBlank(None, False, False))
        self.assertEqual('', strlib.normalizeBlank('', False, False))

        # normalizeBlank(blank, False, False)
        self.assertEqual(' ', strlib.normalizeBlank(' ', False, False))
        self.assertEqual('  ', strlib.normalizeBlank('  ', False, False))

        # normalizeBlank(value, False, False)
        self.assertEqual('abc', strlib.normalizeBlank('abc', False, False))
        self.assertEqual(' abc', strlib.normalizeBlank(' abc', False, False))
        self.assertEqual('abc ', strlib.normalizeBlank('abc ', False, False))
        self.assertEqual(' abc ', strlib.normalizeBlank(' abc ', False, False))

        # False, True
        # normalizeBlank(None, False, True), normalizeBlank(empty, False, True)
        self.assertIsNone(strlib.normalizeBlank(None, False, True))
        self.assertIsNone(strlib.normalizeBlank('', False, True))

        # normalizeBlank(blank, False, True)
        self.assertIsNone(strlib.normalizeBlank(' ', False, True))
        self.assertIsNone(strlib.normalizeBlank('  ', False, True))

        # normalizeBlank(value, False, True)
        self.assertEqual('abc', strlib.normalizeBlank('abc', False, True))
        self.assertEqual(' abc', strlib.normalizeBlank(' abc', False, True))
        self.assertEqual('abc ', strlib.normalizeBlank('abc ', False, True))
        self.assertEqual(' abc ', strlib.normalizeBlank(' abc ', False, True))

        # True, True
        # normalizeBlank(None, True, True), normalizeBlank(empty, True, True)
        self.assertIsNone(strlib.normalizeBlank(None, True, True))
        self.assertIsNone(strlib.normalizeBlank('', True, True))

        # normalizeBlank(blank, True, True)
        self.assertIsNone(strlib.normalizeBlank(' ', True, True))
        self.assertIsNone(strlib.normalizeBlank('  ', True, True))

        # normalizeBlank(value, True, True)
        self.assertEqual('abc', strlib.normalizeBlank('abc', True, True))
        self.assertEqual('abc', strlib.normalizeBlank(' abc', True, True))
        self.assertEqual('abc', strlib.normalizeBlank('abc ', True, True))
        self.assertEqual('abc', strlib.normalizeBlank(' abc ', True, True))

        # True, False
        # normalizeBlank(None, True, False), normalizeBlank(empty, True, False)
        self.assertIsNone(strlib.normalizeBlank(None, True, False))
        self.assertIsNone(strlib.normalizeBlank('', True, False))

        # normalizeBlank(blank, true, false)
        self.assertIsNone(strlib.normalizeBlank(' ', True, False))
        self.assertIsNone(strlib.normalizeBlank('  ', True, False))

        # normalizeBlank(value, true, false)
        self.assertEqual('abc', strlib.normalizeBlank('abc', True, False))
        self.assertEqual('abc', strlib.normalizeBlank(' abc', True, False))
        self.assertEqual('abc', strlib.normalizeBlank('abc ', True, False))
        self.assertEqual('abc', strlib.normalizeBlank(' abc ', True, False))

    
    # - emptyIfNone(str)                                           - None -> ''
    # - noneIfEmpty(str)                                           - '' -> None
    # - noneIfEmpty(str, trim)                                     - trim, '' -> None

    def test_defaultIfNone(self):

        # defaultIfNone(None/empty, None/empty)
        self.assertIsNone(strlib.defaultIfNone(None, None))
        self.assertEqual('', strlib.defaultIfNone('', None))
        self.assertEqual('', strlib.defaultIfNone(None, ''))
        self.assertEqual('', strlib.defaultIfNone('', ''))
        
        self.assertEqual(' ', strlib.defaultIfNone(' ', None))
        self.assertEqual(' ', strlib.defaultIfNone(None, ' '))
        self.assertEqual('', strlib.defaultIfNone('', ' '))     # 1
        self.assertEqual(' ', strlib.defaultIfNone(' ', ' '))

        self.assertEqual(' ', strlib.defaultIfNone(' ', ''))
        self.assertEqual('', strlib.defaultIfNone('', ' '))     # 2   
        self.assertEqual('', strlib.defaultIfNone('', ' '))     # 3

        self.assertEqual('.', strlib.defaultIfNone('.', None))
        self.assertEqual('.', strlib.defaultIfNone(None, '.'))
        self.assertEqual('', strlib.defaultIfNone('', '.'))     # 4 
        self.assertEqual('.', strlib.defaultIfNone('.', '.'))

        self.assertEqual('.', strlib.defaultIfNone('.', ''))
        self.assertEqual('', strlib.defaultIfNone('', '.'))     # 5
        self.assertEqual('', strlib.defaultIfNone('', '.'))     # 6

    def test_defaultIfEmpty(self):

        # defaultIfEmpty(None/empty, None/empty)
        self.assertIsNone(strlib.defaultIfEmpty(None, None))
        self.assertIsNone(strlib.defaultIfEmpty('', None))
        self.assertEqual('', strlib.defaultIfEmpty(None, ''))
        self.assertEqual('', strlib.defaultIfEmpty('', ''))
        
        self.assertEqual(' ', strlib.defaultIfEmpty(' ', None))
        self.assertEqual(' ', strlib.defaultIfEmpty(None, ' '))
        self.assertEqual(' ', strlib.defaultIfEmpty('', ' '))
        self.assertEqual(' ', strlib.defaultIfEmpty(' ', ' '))

        self.assertEqual(' ', strlib.defaultIfEmpty(' ', ''))
        self.assertEqual(' ', strlib.defaultIfEmpty('', ' '))        
        self.assertEqual(' ', strlib.defaultIfEmpty('', ' '))

        self.assertEqual('.', strlib.defaultIfEmpty('.', None))
        self.assertEqual('.', strlib.defaultIfEmpty(None, '.'))
        self.assertEqual('.', strlib.defaultIfEmpty('', '.'))
        self.assertEqual('.', strlib.defaultIfEmpty('.', '.'))

        self.assertEqual('.', strlib.defaultIfEmpty('.', ''))
        self.assertEqual('.', strlib.defaultIfEmpty('', '.'))
        self.assertEqual('.', strlib.defaultIfEmpty('', '.'))

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

    def test_contains(self):

        # False: contains(None/empty, None/empty)
        self.assertFalse(strlib.contains(None, None))
        self.assertFalse(strlib.contains('', None))
        self.assertFalse(strlib.contains(None, ''))
        self.assertFalse(strlib.contains('', ''))

        self.assertFalse(strlib.contains(' ', None))
        self.assertFalse(strlib.contains(None, ' '))

        # False: contains(blank, blank)
        self.assertTrue(strlib.contains(' ', ' '))
        self.assertTrue(strlib.contains('  ', '  '))
        self.assertTrue(strlib.contains('.', '.'))

        # True: contains(value, value)
        self.assertTrue(strlib.contains('a', 'a'))
        self.assertTrue(strlib.contains('abc', 'a'))
        self.assertTrue(strlib.contains('abc', 'b'))
        self.assertTrue(strlib.contains('abc', 'c'))

        self.assertTrue(strlib.contains('abc', 'ab'))
        self.assertTrue(strlib.contains('abc', 'bc'))
        self.assertTrue(strlib.contains('abc', 'abc'))

        # False: contains(value, value)
        self.assertFalse(strlib.contains('abc', 'ac'))

        self.assertFalse(strlib.contains('abc', '.'))
        self.assertFalse(strlib.contains('abc', 'x'))
        self.assertFalse(strlib.contains('abc', 'y'))
        self.assertFalse(strlib.contains('abc', 'z'))
        
        self.assertFalse(strlib.contains('abc', 'def'))
        self.assertFalse(strlib.contains('abc', 'xyz'))

    def test_findFirst(self):

        # char
        
        # NotFound: None/empty
        self.assertEqual(-1, strlib.findFirst(None, '\0'))
        self.assertEqual(-1, strlib.findFirst('', '\0'))
        self.assertEqual(-1, strlib.findFirst(None, ' '))
        self.assertEqual(-1, strlib.findFirst('', ' '))
        self.assertEqual(-1, strlib.findFirst(None, '.'))
        self.assertEqual(-1, strlib.findFirst('', '.'))
        
        # NotFound: None/empty, pos
        self.assertEqual(-1, strlib.findFirst(None, '\0', 0))
        self.assertEqual(-1, strlib.findFirst('', '\0', 0))
        self.assertEqual(-1, strlib.findFirst(None, ' ', 0))
        self.assertEqual(-1, strlib.findFirst('', ' ', 0))
        self.assertEqual(-1, strlib.findFirst(None, '.', 0))
        self.assertEqual(-1, strlib.findFirst('', '.', 0))

        self.assertEqual(-1, strlib.findFirst(None, '\0', -1))
        self.assertEqual(-1, strlib.findFirst('', '\0', -1))
        self.assertEqual(-1, strlib.findFirst(None, ' ', -1))
        self.assertEqual(-1, strlib.findFirst('', ' ', -1))
        self.assertEqual(-1, strlib.findFirst(None, '.', -1))
        self.assertEqual(-1, strlib.findFirst('', '.', -1))

        self.assertEqual(-1, strlib.findFirst(None, '\0', 1))
        self.assertEqual(-1, strlib.findFirst('', '\0', 1))
        self.assertEqual(-1, strlib.findFirst(None, ' ', 1))
        self.assertEqual(-1, strlib.findFirst('', ' ', 1))
        self.assertEqual(-1, strlib.findFirst(None, '.', 1))
        self.assertEqual(-1, strlib.findFirst('', '.', 1))
        
        # NotFound: blank/value
        self.assertEqual(0, strlib.findFirst(' ', ' '))
        self.assertEqual(0, strlib.findFirst('  ', ' '))
        
        # Found: blank/value
        self.assertEqual(0, strlib.findFirst(' .', ' '))
        self.assertEqual(0, strlib.findFirst('  .', ' '))

        self.assertEqual(1, strlib.findFirst('. ', ' '))
        self.assertEqual(1, strlib.findFirst('.  ', ' '))

        # Found: value
        self.assertEqual(0, strlib.findFirst('*', '*'))

        self.assertEqual(1, strlib.findFirst('.*', '*'))
        self.assertEqual(2, strlib.findFirst('..*', '*'))
        self.assertEqual(3, strlib.findFirst('...*', '*'))

        self.assertEqual(0, strlib.findFirst('*.', '*'))
        self.assertEqual(0, strlib.findFirst('*..', '*'))
        self.assertEqual(0, strlib.findFirst('*...', '*'))

        # NotFound: value, pos, min range
        self.assertEqual(-1, strlib.findFirst('**..**..', '*', -1))
        self.assertEqual(-1, strlib.findFirst('**..**..', '*', -2))

        # Found: value, pos
        self.assertEqual(0, strlib.findFirst('**..**..', '*', 0))
        self.assertEqual(1, strlib.findFirst('**..**..', '*', 1))
        self.assertEqual(4, strlib.findFirst('**..**..', '*', 2))
        self.assertEqual(4, strlib.findFirst('**..**..', '*', 3))
        self.assertEqual(4, strlib.findFirst('**..**..', '*', 4))
        self.assertEqual(5, strlib.findFirst('**..**..', '*', 5))

        # NotFound: value, pos
        self.assertEqual(-1, strlib.findFirst('**..**..', '*', 6))
        self.assertEqual(-1, strlib.findFirst('**..**..', '*', 7))

        # NotFound: value, pos, max range
        self.assertEqual(-1, strlib.findFirst('**..**..', '*', 8))
        self.assertEqual(-1, strlib.findFirst('**..**..', '*', 9))

    def test_findLast(self):

        # char
        
        # NotFound: None/empty
        self.assertEqual(-1, strlib.findLast(None, '\0'))
        self.assertEqual(-1, strlib.findLast('', '\0'))
        self.assertEqual(-1, strlib.findLast(None, ' '))
        self.assertEqual(-1, strlib.findLast('', ' '))
        self.assertEqual(-1, strlib.findLast(None, '.'))
        self.assertEqual(-1, strlib.findLast('', '.'))
        
        # NotFound: None/empty, pos
        self.assertEqual(-1, strlib.findLast(None, '\0', 0))
        self.assertEqual(-1, strlib.findLast('', '\0', 0))
        self.assertEqual(-1, strlib.findLast(None, ' ', 0))
        self.assertEqual(-1, strlib.findLast('', ' ', 0))
        self.assertEqual(-1, strlib.findLast(None, '.', 0))
        self.assertEqual(-1, strlib.findLast('', '.', 0))

        self.assertEqual(-1, strlib.findLast(None, '\0', -1))
        self.assertEqual(-1, strlib.findLast('', '\0', -1))
        self.assertEqual(-1, strlib.findLast(None, ' ', -1))
        self.assertEqual(-1, strlib.findLast('', ' ', -1))
        self.assertEqual(-1, strlib.findLast(None, '.', -1))
        self.assertEqual(-1, strlib.findLast('', '.', -1))

        self.assertEqual(-1, strlib.findLast(None, '\0', 1))
        self.assertEqual(-1, strlib.findLast('', '\0', 1))
        self.assertEqual(-1, strlib.findLast(None, ' ', 1))
        self.assertEqual(-1, strlib.findLast('', ' ', 1))
        self.assertEqual(-1, strlib.findLast(None, '.', 1))
        self.assertEqual(-1, strlib.findLast('', '.', 1))
        
        # Found: blank
        self.assertEqual(0, strlib.findLast(' ', ' '))
        self.assertEqual(1, strlib.findLast('  ', ' '))

        # Found: blank/value
        self.assertEqual(0, strlib.findLast(' .', ' '))
        self.assertEqual(1, strlib.findLast('  .', ' '))

        self.assertEqual(1, strlib.findLast('. ', ' '))
        self.assertEqual(2, strlib.findLast('.  ', ' '))

        # Found: value
        self.assertEqual(0, strlib.findLast('*', '*'))

        self.assertEqual(0, strlib.findLast('*.', '*'))
        self.assertEqual(0, strlib.findLast('*..', '*'))
        self.assertEqual(0, strlib.findLast('*...', '*'))

        self.assertEqual(1, strlib.findLast('.*', '*'))
        self.assertEqual(2, strlib.findLast('..*', '*'))
        self.assertEqual(3, strlib.findLast('...*', '*'))

        # NotFound: value, pos, min range
        self.assertEqual(-1, strlib.findLast('..**..**', '*', -1))
        self.assertEqual(-1, strlib.findLast('..**..**', '*', -2))

        # NotFound: value, pos
        self.assertEqual(-1, strlib.findLast('..**..**', '*', 0))
        self.assertEqual(-1, strlib.findLast('..**..**', '*', 1))

        # Found: value, pos
        self.assertEqual(2, strlib.findLast('..**..**', '*', 2))
        self.assertEqual(3, strlib.findLast('..**..**', '*', 3))
        self.assertEqual(3, strlib.findLast('..**..**', '*', 4))
        self.assertEqual(3, strlib.findLast('..**..**', '*', 5))
        self.assertEqual(6, strlib.findLast('..**..**', '*', 6))
        self.assertEqual(7, strlib.findLast('..**..**', '*', 7))

        # NotFound: value, pos, max range
        self.assertEqual(-1, strlib.findLast('..**..**', '*', 8))
        self.assertEqual(-1, strlib.findLast('..**..**', '*', 9))

    def test_findFirstOf(self):

        # char
        
        # NotFound: None/empty
        self.assertEqual(-1, strlib.findFirstOf(None, '\0'))
        self.assertEqual(-1, strlib.findFirstOf('', '\0'))
        self.assertEqual(-1, strlib.findFirstOf(None, ' '))
        self.assertEqual(-1, strlib.findFirstOf('', ' '))
        self.assertEqual(-1, strlib.findFirstOf(None, '.'))
        self.assertEqual(-1, strlib.findFirstOf('', '.'))
        
        # NotFound: None/empty, pos
        self.assertEqual(-1, strlib.findFirstOf(None, '\0', 0))
        self.assertEqual(-1, strlib.findFirstOf('', '\0', 0))
        self.assertEqual(-1, strlib.findFirstOf(None, ' ', 0))
        self.assertEqual(-1, strlib.findFirstOf('', ' ', 0))
        self.assertEqual(-1, strlib.findFirstOf(None, '.', 0))
        self.assertEqual(-1, strlib.findFirstOf('', '.', 0))

        self.assertEqual(-1, strlib.findFirstOf(None, '\0', -1))
        self.assertEqual(-1, strlib.findFirstOf('', '\0', -1))
        self.assertEqual(-1, strlib.findFirstOf(None, ' ', -1))
        self.assertEqual(-1, strlib.findFirstOf('', ' ', -1))
        self.assertEqual(-1, strlib.findFirstOf(None, '.', -1))
        self.assertEqual(-1, strlib.findFirstOf('', '.', -1))

        self.assertEqual(-1, strlib.findFirstOf(None, '\0', 1))
        self.assertEqual(-1, strlib.findFirstOf('', '\0', 1))
        self.assertEqual(-1, strlib.findFirstOf(None, ' ', 1))
        self.assertEqual(-1, strlib.findFirstOf('', ' ', 1))
        self.assertEqual(-1, strlib.findFirstOf(None, '.', 1))
        self.assertEqual(-1, strlib.findFirstOf('', '.', 1))
        
        # NotFound: blank/value
        self.assertEqual(0, strlib.findFirstOf(' ', ' '))
        self.assertEqual(0, strlib.findFirstOf('  ', ' '))
        
        # Found: blank/value
        self.assertEqual(0, strlib.findFirstOf(' .', ' '))
        self.assertEqual(0, strlib.findFirstOf('  .', ' '))

        self.assertEqual(1, strlib.findFirstOf('. ', ' '))
        self.assertEqual(1, strlib.findFirstOf('.  ', ' '))

        # Found: value
        self.assertEqual(0, strlib.findFirstOf('*', '*'))

        self.assertEqual(1, strlib.findFirstOf('.*', '*'))
        self.assertEqual(2, strlib.findFirstOf('..*', '*'))
        self.assertEqual(3, strlib.findFirstOf('...*', '*'))

        self.assertEqual(0, strlib.findFirstOf('*.', '*'))
        self.assertEqual(0, strlib.findFirstOf('*..', '*'))
        self.assertEqual(0, strlib.findFirstOf('*...', '*'))

        # NotFound: value, pos, min range
        self.assertEqual(-1, strlib.findFirstOf('**..**..', '*', -1))
        self.assertEqual(-1, strlib.findFirstOf('**..**..', '*', -2))

        # Found: value, pos
        self.assertEqual(0, strlib.findFirstOf('**..**..', '*', 0))
        self.assertEqual(1, strlib.findFirstOf('**..**..', '*', 1))
        self.assertEqual(4, strlib.findFirstOf('**..**..', '*', 2))
        self.assertEqual(4, strlib.findFirstOf('**..**..', '*', 3))
        self.assertEqual(4, strlib.findFirstOf('**..**..', '*', 4))
        self.assertEqual(5, strlib.findFirstOf('**..**..', '*', 5))

        # NotFound: value, pos
        self.assertEqual(-1, strlib.findFirstOf('**..**..', '*', 6))
        self.assertEqual(-1, strlib.findFirstOf('**..**..', '*', 7))

        # NotFound: value, pos, max range
        self.assertEqual(-1, strlib.findFirstOf('**..**..', '*', 8))
        self.assertEqual(-1, strlib.findFirstOf('**..**..', '*', 9))

    def test_findLastOf(self):

        # char
        
        # NotFound: None/empty
        self.assertEqual(-1, strlib.findLastOf(None, '\0'))
        self.assertEqual(-1, strlib.findLastOf('', '\0'))
        self.assertEqual(-1, strlib.findLastOf(None, ' '))
        self.assertEqual(-1, strlib.findLastOf('', ' '))
        self.assertEqual(-1, strlib.findLastOf(None, '.'))
        self.assertEqual(-1, strlib.findLastOf('', '.'))
        
        # NotFound: None/empty, pos
        self.assertEqual(-1, strlib.findLastOf(None, '\0', 0))
        self.assertEqual(-1, strlib.findLastOf('', '\0', 0))
        self.assertEqual(-1, strlib.findLastOf(None, ' ', 0))
        self.assertEqual(-1, strlib.findLastOf('', ' ', 0))
        self.assertEqual(-1, strlib.findLastOf(None, '.', 0))
        self.assertEqual(-1, strlib.findLastOf('', '.', 0))

        self.assertEqual(-1, strlib.findLastOf(None, '\0', -1))
        self.assertEqual(-1, strlib.findLastOf('', '\0', -1))
        self.assertEqual(-1, strlib.findLastOf(None, ' ', -1))
        self.assertEqual(-1, strlib.findLastOf('', ' ', -1))
        self.assertEqual(-1, strlib.findLastOf(None, '.', -1))
        self.assertEqual(-1, strlib.findLastOf('', '.', -1))

        self.assertEqual(-1, strlib.findLastOf(None, '\0', 1))
        self.assertEqual(-1, strlib.findLastOf('', '\0', 1))
        self.assertEqual(-1, strlib.findLastOf(None, ' ', 1))
        self.assertEqual(-1, strlib.findLastOf('', ' ', 1))
        self.assertEqual(-1, strlib.findLastOf(None, '.', 1))
        self.assertEqual(-1, strlib.findLastOf('', '.', 1))
        
        # Found: blank
        self.assertEqual(0, strlib.findLastOf(' ', ' '))
        self.assertEqual(1, strlib.findLastOf('  ', ' '))

        # Found: blank/value
        self.assertEqual(0, strlib.findLastOf(' .', ' '))
        self.assertEqual(1, strlib.findLastOf('  .', ' '))

        self.assertEqual(1, strlib.findLastOf('. ', ' '))
        self.assertEqual(2, strlib.findLastOf('.  ', ' '))

        # Found: value
        self.assertEqual(0, strlib.findLastOf('*', '*'))

        self.assertEqual(0, strlib.findLastOf('*.', '*'))
        self.assertEqual(0, strlib.findLastOf('*..', '*'))
        self.assertEqual(0, strlib.findLastOf('*...', '*'))

        self.assertEqual(1, strlib.findLastOf('.*', '*'))
        self.assertEqual(2, strlib.findLastOf('..*', '*'))
        self.assertEqual(3, strlib.findLastOf('...*', '*'))

        # NotFound: value, pos, min range
        self.assertEqual(-1, strlib.findLastOf('..**..**', '*', -1))
        self.assertEqual(-1, strlib.findLastOf('..**..**', '*', -2))

        # NotFound: value, pos
        self.assertEqual(-1, strlib.findLastOf('..**..**', '*', 0))
        self.assertEqual(-1, strlib.findLastOf('..**..**', '*', 1))

        # Found: value, pos
        self.assertEqual(2, strlib.findLastOf('..**..**', '*', 2))
        self.assertEqual(3, strlib.findLastOf('..**..**', '*', 3))
        self.assertEqual(3, strlib.findLastOf('..**..**', '*', 4))
        self.assertEqual(3, strlib.findLastOf('..**..**', '*', 5))
        self.assertEqual(6, strlib.findLastOf('..**..**', '*', 6))
        self.assertEqual(7, strlib.findLastOf('..**..**', '*', 7))

        # NotFound: value, pos, max range
        self.assertEqual(-1, strlib.findLastOf('..**..**', '*', 8))
        self.assertEqual(-1, strlib.findLastOf('..**..**', '*', 9))

    def test_findFirstNotOf(self):
        
        # NotFound: None/empty
        self.assertEqual(-1, strlib.findFirstNotOf(None, '\0'))
        self.assertEqual(-1, strlib.findFirstNotOf('', '\0'))
        self.assertEqual(-1, strlib.findFirstNotOf(None, ' '))
        self.assertEqual(-1, strlib.findFirstNotOf('', ' '))
        self.assertEqual(-1, strlib.findFirstNotOf(None, '.'))
        self.assertEqual(-1, strlib.findFirstNotOf('', '.'))

        # NotFound: None/empty, pos
        self.assertEqual(-1, strlib.findFirstNotOf(None, '\0', 0))
        self.assertEqual(-1, strlib.findFirstNotOf('', '\0', 0))
        self.assertEqual(-1, strlib.findFirstNotOf(None, ' ', 0))
        self.assertEqual(-1, strlib.findFirstNotOf('', ' ', 0))
        self.assertEqual(-1, strlib.findFirstNotOf(None, '.', 0))
        self.assertEqual(-1, strlib.findFirstNotOf('', '.', 0))

        self.assertEqual(-1, strlib.findFirstNotOf(None, '\0', -1))
        self.assertEqual(-1, strlib.findFirstNotOf('', '\0', -1))
        self.assertEqual(-1, strlib.findFirstNotOf(None, ' ', -1))
        self.assertEqual(-1, strlib.findFirstNotOf('', ' ', -1))
        self.assertEqual(-1, strlib.findFirstNotOf(None, '.', -1))
        self.assertEqual(-1, strlib.findFirstNotOf('', '.', -1))

        self.assertEqual(-1, strlib.findFirstNotOf(None, '\0', 1))
        self.assertEqual(-1, strlib.findFirstNotOf('', '\0', 1))
        self.assertEqual(-1, strlib.findFirstNotOf(None, ' ', 1))
        self.assertEqual(-1, strlib.findFirstNotOf('', ' ', 1))
        self.assertEqual(-1, strlib.findFirstNotOf(None, '.', 1))
        self.assertEqual(-1, strlib.findFirstNotOf('', '.', 1))

        # NotFound: blank/value
        self.assertEqual(-1, strlib.findFirstNotOf(' ', ' '))
        self.assertEqual(-1, strlib.findFirstNotOf('  ', ' '))

        # Found: value
        self.assertEqual(0, strlib.findFirstNotOf('.', ' '))
        self.assertEqual(1, strlib.findFirstNotOf(' .', ' '))
        self.assertEqual(2, strlib.findFirstNotOf('  .', ' '))

        self.assertEqual(0, strlib.findFirstNotOf('.', '*'))
        self.assertEqual(1, strlib.findFirstNotOf('*.', '*'))
        self.assertEqual(2, strlib.findFirstNotOf('**.', '*'))

        # Found: value, pos
        self.assertEqual(2, strlib.findFirstNotOf('**..**..', '*', 0))
        self.assertEqual(2, strlib.findFirstNotOf('**..**..', '*', 1))
        self.assertEqual(2, strlib.findFirstNotOf('**..**..', '*', 2))
        self.assertEqual(3, strlib.findFirstNotOf('**..**..', '*', 3))
        self.assertEqual(6, strlib.findFirstNotOf('**..**..', '*', 4))
        self.assertEqual(6, strlib.findFirstNotOf('**..**..', '*', 5))
        self.assertEqual(6, strlib.findFirstNotOf('**..**..', '*', 6))
        self.assertEqual(7, strlib.findFirstNotOf('**..**..', '*', 7))

        # NotFound: value, pos
        self.assertEqual(-1, strlib.findFirstNotOf('**..**..', '*', 8))

    def test_findLastNotOf(self):
        
        # NotFound: None/empty
        self.assertEqual(-1, strlib.findLastNotOf(None, '\0'))
        self.assertEqual(-1, strlib.findLastNotOf('', '\0'))
        self.assertEqual(-1, strlib.findLastNotOf(None, ' '))
        self.assertEqual(-1, strlib.findLastNotOf('', ' '))
        self.assertEqual(-1, strlib.findLastNotOf(None, '.'))
        self.assertEqual(-1, strlib.findLastNotOf('', '.'))

        # NotFound: None/empty, pos
        self.assertEqual(-1, strlib.findLastNotOf(None, '\0', 0))
        self.assertEqual(-1, strlib.findLastNotOf('', '\0', 0))
        self.assertEqual(-1, strlib.findLastNotOf(None, ' ', 0))
        self.assertEqual(-1, strlib.findLastNotOf('', ' ', 0))
        self.assertEqual(-1, strlib.findLastNotOf(None, '.', 0))
        self.assertEqual(-1, strlib.findLastNotOf('', '.', 0))

        self.assertEqual(-1, strlib.findLastNotOf(None, '\0', -1))
        self.assertEqual(-1, strlib.findLastNotOf('', '\0', -1))
        self.assertEqual(-1, strlib.findLastNotOf(None, ' ', -1))
        self.assertEqual(-1, strlib.findLastNotOf('', ' ', -1))
        self.assertEqual(-1, strlib.findLastNotOf(None, '.', -1))
        self.assertEqual(-1, strlib.findLastNotOf('', '.', -1))

        self.assertEqual(-1, strlib.findLastNotOf(None, '\0', 1))
        self.assertEqual(-1, strlib.findLastNotOf('', '\0', 1))
        self.assertEqual(-1, strlib.findLastNotOf(None, ' ', 1))
        self.assertEqual(-1, strlib.findLastNotOf('', ' ', 1))
        self.assertEqual(-1, strlib.findLastNotOf(None, '.', 1))
        self.assertEqual(-1, strlib.findLastNotOf('', '.', 1))

        # NotFound: blank/value
        self.assertEqual(-1, strlib.findLastNotOf(' ', ' '))
        self.assertEqual(-1, strlib.findLastNotOf('  ', ' '))

        # Found: value
        self.assertEqual(0, strlib.findLastNotOf('.', ' '))
        self.assertEqual(1, strlib.findLastNotOf('.. ', ' '))
        self.assertEqual(1, strlib.findLastNotOf('..  ', ' '))

        self.assertEqual(0, strlib.findLastNotOf('.', '*'))
        self.assertEqual(1, strlib.findLastNotOf('..*', '*'))
        self.assertEqual(1, strlib.findLastNotOf('..**', '*'))

        # NotFound: value, pos, min range
        self.assertEqual(-1, strlib.findLastNotOf('..**..**', '*', -1))
        self.assertEqual(-1, strlib.findLastNotOf('..**..**', '*', -2))

        # Found: value, pos
        self.assertEqual(0, strlib.findLastNotOf('..**..**', '*', 0))
        self.assertEqual(1, strlib.findLastNotOf('..**..**', '*', 1))
        self.assertEqual(1, strlib.findLastNotOf('..**..**', '*', 2))
        self.assertEqual(1, strlib.findLastNotOf('..**..**', '*', 3))
        self.assertEqual(4, strlib.findLastNotOf('..**..**', '*', 4))
        self.assertEqual(5, strlib.findLastNotOf('..**..**', '*', 5))
        self.assertEqual(5, strlib.findLastNotOf('..**..**', '*', 6))
        self.assertEqual(5, strlib.findLastNotOf('..**..**', '*', 7))

        # NotFound: value, pos, max range
        self.assertEqual(-1, strlib.findLastNotOf('..**..**', '*', 8))
        self.assertEqual(-1, strlib.findLastNotOf('..**..**', '*', 9))

if __name__ == '__main__':
    unittest.main()
