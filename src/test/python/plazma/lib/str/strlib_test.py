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

    def test_normalize(self):

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

    def test_normalizeSafe(self):

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

    def test_normalizeBlank(self):

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

    def test_trimAll(self): # alias: trim(str)

        # trimAll(None), trimAll(empty)
        self.assertIsNone(strlib.trimAll(None))        
        self.assertEqual('', strlib.trimAll(''))

        # trimAll(blank)
        self.assertEqual('', strlib.trimAll(' '))
        self.assertEqual('', strlib.trimAll('  '))
        
        # trimAll(value)
        self.assertEqual('abc', strlib.trimAll('abc'))
        self.assertEqual('abc', strlib.trimAll(' abc'))
        self.assertEqual('abc', strlib.trimAll('abc '))
        self.assertEqual('abc', strlib.trimAll(' abc '))

        # trimAll(' \t\n\r\f\v')
        self.assertEqual('', strlib.trimAll(' \t\n\r\f\v'))
        self.assertEqual('abc', strlib.trimAll(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.trimAll('abc \t\n\r\f\v'))
        self.assertEqual('abc', strlib.trimAll(' \t\n\r\f\vabc \t\n\r\f\v'))

    def test_trimSpace(self):

        # trimSpace(None), trimSpace(empty)
        self.assertIsNone(strlib.trimSpace(None))        
        self.assertEqual('', strlib.trimSpace(''))

        # trimSpace(blank)
        self.assertEqual('', strlib.trimSpace(' '))
        self.assertEqual('', strlib.trimSpace('  '))
        
        # trimSpace(value)
        self.assertEqual('abc', strlib.trimSpace('abc'))
        self.assertEqual('abc', strlib.trimSpace(' abc'))
        self.assertEqual('abc', strlib.trimSpace('abc '))
        self.assertEqual('abc', strlib.trimSpace(' abc '))

        # trimSpace(' \t\n\r\f\v')
        self.assertEqual('\t\n\r\f\v', strlib.trimSpace(' \t\n\r\f\v'))
        self.assertEqual('\t\n\r\f\vabc', strlib.trimSpace(' \t\n\r\f\vabc'))
        self.assertEqual('abc \t\n\r\f\v', strlib.trimSpace('abc \t\n\r\f\v'))
        self.assertEqual('\t\n\r\f\vabc \t\n\r\f\v', strlib.trimSpace(' \t\n\r\f\vabc \t\n\r\f\v'))

    def test_ltrim(self):

        # ltrim(None), ltrim(empty)
        self.assertIsNone(strlib.ltrim(None))
        self.assertEqual('', strlib.ltrim(''))

        # ltrim(blank)
        self.assertEqual('', strlib.ltrim(' '))
        self.assertEqual('', strlib.ltrim('  '))

        # ltrim(value)
        self.assertEqual('abc', strlib.ltrim('abc'))
        self.assertEqual('abc', strlib.ltrim(' abc'))
        self.assertEqual('abc ', strlib.ltrim('abc '))
        self.assertEqual('abc ', strlib.ltrim(' abc '))

        # ltrim(' \t\n\r\f\v')
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

        # rtrim(value)
        self.assertEqual('abc', strlib.rtrim('abc'))
        self.assertEqual(' abc', strlib.rtrim(' abc'))
        self.assertEqual('abc', strlib.rtrim('abc '))
        self.assertEqual(' abc', strlib.rtrim(' abc '))
        
        # rtrim(' \t\n\r\f\v')
        self.assertEqual('', strlib.rtrim(' \t\n\r\f\v'))
        self.assertEqual(' \t\n\r\f\vabc', strlib.rtrim(' \t\n\r\f\vabc'))
        self.assertEqual('abc', strlib.rtrim('abc \t\n\r\f\v'))
        self.assertEqual(' \t\n\r\f\vabc', strlib.rtrim(' \t\n\r\f\vabc \t\n\r\f\v'))

    # 1.4

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

    # 2.1

    def test_replicate(self):

        # replicate(None, -n), replicate(None, 0), replicate(None, n)
        self.assertIsNone(strlib.replicate(None, -2))
        self.assertIsNone(strlib.replicate(None, -1))
        self.assertIsNone(strlib.replicate(None, 0))
        self.assertIsNone(strlib.replicate(None, 1))
        self.assertIsNone(strlib.replicate(None, 2))

        # replicate(empty, -n), replicate(empty, 0), replicate(empty, n)
        self.assertEqual('', strlib.replicate('', -2))
        self.assertEqual('', strlib.replicate('', -1))
        self.assertEqual('', strlib.replicate('', 0))
        self.assertEqual('', strlib.replicate('', 1))
        self.assertEqual('', strlib.replicate('', 2))

        # char

        # replicate(char, -n), replicate(char, 0), replicate(char, n)
        self.assertEqual('', strlib.replicate('.', -2))
        self.assertEqual('', strlib.replicate('.', -1))
        self.assertEqual('', strlib.replicate('.', 0))
        self.assertEqual('.', strlib.replicate('.', 1))
        self.assertEqual('..', strlib.replicate('.', 2))

        # replicate(char, n)
        self.assertEqual('*', strlib.replicate('*', 1))
        self.assertEqual('**', strlib.replicate('*', 2))
        self.assertEqual('***', strlib.replicate('*', 3))

        self.assertEqual('a', strlib.replicate('a', 1))
        self.assertEqual('aa', strlib.replicate('a', 2))
        self.assertEqual('aaa', strlib.replicate('a', 3))

        # string

        # replicate(str, -n), replicate(str, 0), replicate(str, n)
        self.assertEqual('', strlib.replicate('abc', -2))
        self.assertEqual('', strlib.replicate('abc', -1))
        self.assertEqual('', strlib.replicate('abc', 0))
        self.assertEqual('abc', strlib.replicate('abc', 1))
        self.assertEqual('abcabc', strlib.replicate('abc', 2))
        self.assertEqual('abcabcabc', strlib.replicate('abc', 3))

    # 2.2

    def test_lpad(self):

        # None

        # lpad(None, -n), lpad(None, 0), lpad(None, n)
        self.assertIsNone(strlib.lpad(None, -2))
        self.assertIsNone(strlib.lpad(None, -1))
        self.assertIsNone(strlib.lpad(None, 0))
        self.assertIsNone(strlib.lpad(None, 1))
        self.assertIsNone(strlib.lpad(None, 2))

        # lpad(None, -n, ''), lpad(None, 0, ''), lpad(None, n, '')
        self.assertIsNone(strlib.lpad(None, -2, ''))
        self.assertIsNone(strlib.lpad(None, -1, ''))
        self.assertIsNone(strlib.lpad(None, 0, ''))
        self.assertIsNone(strlib.lpad(None, 1, ''))
        self.assertIsNone(strlib.lpad(None, 2, ''))

        # lpad(None, -n, ' '), lpad(None, 0, ' '), lpad(None, n, ' ')
        self.assertIsNone(strlib.lpad(None, -2, ' '))
        self.assertIsNone(strlib.lpad(None, -1, ' '))
        self.assertIsNone(strlib.lpad(None, 0, ' '))
        self.assertIsNone(strlib.lpad(None, 1, ' '))
        self.assertIsNone(strlib.lpad(None, 2, ' '))

        # lpad(None, -n, '*'), lpad(None, 0, '*'), lpad(None, n, '*')
        self.assertIsNone(strlib.lpad(None, -2, '*'))
        self.assertIsNone(strlib.lpad(None, -1, '*'))
        self.assertIsNone(strlib.lpad(None, 0, '*'))
        self.assertIsNone(strlib.lpad(None, 1, '*'))
        self.assertIsNone(strlib.lpad(None, 2, '*'))

        # empty

        # lpad(empty, -n), lpad(empty, 0), lpad(empty, n)
        self.assertEqual('', strlib.lpad('', -2))
        self.assertEqual('', strlib.lpad('', -1))
        self.assertEqual('', strlib.lpad('', 0))
        self.assertEqual(' ', strlib.lpad('', 1))
        self.assertEqual('  ', strlib.lpad('', 2))
        self.assertEqual('   ', strlib.lpad('', 3))

        self.assertEqual('', strlib.lpad('', -2, '*'))
        self.assertEqual('', strlib.lpad('', -1, '*'))
        self.assertEqual('', strlib.lpad('', 0, '*'))
        self.assertEqual('*', strlib.lpad('', 1, '*'))
        self.assertEqual('**', strlib.lpad('', 2, '*'))
        self.assertEqual('***', strlib.lpad('', 3, '*'))

        # char

        # lpad(char, -n), lpad(char, 0), lpad(char, n)
        self.assertEqual('a', strlib.lpad('a', -2))
        self.assertEqual('a', strlib.lpad('a', -1))
        self.assertEqual('a', strlib.lpad('a', 0))
        self.assertEqual('a', strlib.lpad('a', 1))
        self.assertEqual(' a', strlib.lpad('a', 2))
        self.assertEqual('  a', strlib.lpad('a', 3))

        self.assertEqual('a', strlib.lpad('a', -2, '*'))
        self.assertEqual('a', strlib.lpad('a', -1, '*'))
        self.assertEqual('a', strlib.lpad('a', 0, '*'))
        self.assertEqual('a', strlib.lpad('a', 1, '*'))
        self.assertEqual('*a', strlib.lpad('a', 2, '*'))
        self.assertEqual('**a', strlib.lpad('a', 3, '*'))

        # string

        # lpad(str, -n), lpad(str, 0), lpad(str, n)
        self.assertEqual('abc', strlib.lpad('abc', -2))
        self.assertEqual('abc', strlib.lpad('abc', -1))
        self.assertEqual('abc', strlib.lpad('abc', 0))
        self.assertEqual('abc', strlib.lpad('abc', 1))
        self.assertEqual('abc', strlib.lpad('abc', 2))
        self.assertEqual('abc', strlib.lpad('abc', 3))
        self.assertEqual(' abc', strlib.lpad('abc', 4))
        self.assertEqual('  abc', strlib.lpad('abc', 5))

        self.assertEqual('abc', strlib.lpad('abc', -2, '*'))
        self.assertEqual('abc', strlib.lpad('abc', -1, '*'))
        self.assertEqual('abc', strlib.lpad('abc', 0, '*'))
        self.assertEqual('abc', strlib.lpad('abc', 1, '*'))
        self.assertEqual('abc', strlib.lpad('abc', 2, '*'))
        self.assertEqual('abc', strlib.lpad('abc', 3, '*'))
        self.assertEqual('*abc', strlib.lpad('abc', 4, '*'))
        self.assertEqual('**abc', strlib.lpad('abc', 5, '*'))

        self.assertEqual('abc', strlib.lpad('abc', -2, 'yz'))
        self.assertEqual('abc', strlib.lpad('abc', -1, 'yz'))
        self.assertEqual('abc', strlib.lpad('abc', 0, 'yz'))
        self.assertEqual('abc', strlib.lpad('abc', 1, 'yz'))
        self.assertEqual('abc', strlib.lpad('abc', 2, 'yz'))
        self.assertEqual('abc', strlib.lpad('abc', 3, 'yz'))
        self.assertEqual('yabc', strlib.lpad('abc', 4, 'yz'))
        self.assertEqual('yzabc', strlib.lpad('abc', 5, 'yz'))
        self.assertEqual('yzyabc', strlib.lpad('abc', 6, 'yz'))
        self.assertEqual('yzyzabc', strlib.lpad('abc', 7, 'yz'))
        self.assertEqual('yzyzyabc', strlib.lpad('abc', 8, 'yz'))

        # 12345678
        # yzyzyz**
        # *****abc
        #---------
        # yzyzyabc


    def test_rpad(self):

        # None

        # rpad(None, -n), rpad(None, 0), rpad(None, n)
        self.assertIsNone(strlib.rpad(None, -2))
        self.assertIsNone(strlib.rpad(None, -1))
        self.assertIsNone(strlib.rpad(None, 0))
        self.assertIsNone(strlib.rpad(None, 1))
        self.assertIsNone(strlib.rpad(None, 2))

        # rpad(None, -n, ''), rpad(None, 0, ''), rpad(None, n, '')
        self.assertIsNone(strlib.rpad(None, -2, ''))
        self.assertIsNone(strlib.rpad(None, -1, ''))
        self.assertIsNone(strlib.rpad(None, 0, ''))
        self.assertIsNone(strlib.rpad(None, 1, ''))
        self.assertIsNone(strlib.rpad(None, 2, ''))

        # rpad(None, -n, ' '), rpad(None, 0, ' '), rpad(None, n, ' ')
        self.assertIsNone(strlib.rpad(None, -2, ' '))
        self.assertIsNone(strlib.rpad(None, -1, ' '))
        self.assertIsNone(strlib.rpad(None, 0, ' '))
        self.assertIsNone(strlib.rpad(None, 1, ' '))
        self.assertIsNone(strlib.rpad(None, 2, ' '))

        # rpad(None, -n, '*'), rpad(None, 0, '*'), rpad(None, n, '*')
        self.assertIsNone(strlib.rpad(None, -2, '*'))
        self.assertIsNone(strlib.rpad(None, -1, '*'))
        self.assertIsNone(strlib.rpad(None, 0, '*'))
        self.assertIsNone(strlib.rpad(None, 1, '*'))
        self.assertIsNone(strlib.rpad(None, 2, '*'))

        # empty

        # rpad(empty, -n), rpad(empty, 0), rpad(empty, n)
        self.assertEqual('', strlib.rpad('', -2))
        self.assertEqual('', strlib.rpad('', -1))
        self.assertEqual('', strlib.rpad('', 0))
        self.assertEqual(' ', strlib.rpad('', 1))
        self.assertEqual('  ', strlib.rpad('', 2))
        self.assertEqual('   ', strlib.rpad('', 3))

        self.assertEqual('', strlib.rpad('', -2, '*'))
        self.assertEqual('', strlib.rpad('', -1, '*'))
        self.assertEqual('', strlib.rpad('', 0, '*'))
        self.assertEqual('*', strlib.rpad('', 1, '*'))
        self.assertEqual('**', strlib.rpad('', 2, '*'))
        self.assertEqual('***', strlib.rpad('', 3, '*'))

        # char

        # rpad(char, -n), rpad(char, 0), rpad(char, n)
        self.assertEqual('a', strlib.rpad('a', -2))
        self.assertEqual('a', strlib.rpad('a', -1))
        self.assertEqual('a', strlib.rpad('a', 0))
        self.assertEqual('a', strlib.rpad('a', 1))
        self.assertEqual('a ', strlib.rpad('a', 2))
        self.assertEqual('a  ', strlib.rpad('a', 3))

        self.assertEqual('a', strlib.rpad('a', -2, '*'))
        self.assertEqual('a', strlib.rpad('a', -1, '*'))
        self.assertEqual('a', strlib.rpad('a', 0, '*'))
        self.assertEqual('a', strlib.rpad('a', 1, '*'))
        self.assertEqual('a*', strlib.rpad('a', 2, '*'))
        self.assertEqual('a**', strlib.rpad('a', 3, '*'))

        # string

        # rpad(str, -n), rpad(str, 0), rpad(str, n)
        self.assertEqual('abc', strlib.rpad('abc', -2))
        self.assertEqual('abc', strlib.rpad('abc', -1))
        self.assertEqual('abc', strlib.rpad('abc', 0))
        self.assertEqual('abc', strlib.rpad('abc', 1))
        self.assertEqual('abc', strlib.rpad('abc', 2))
        self.assertEqual('abc', strlib.rpad('abc', 3))
        self.assertEqual('abc ', strlib.rpad('abc', 4))
        self.assertEqual('abc  ', strlib.rpad('abc', 5))

        self.assertEqual('abc', strlib.rpad('abc', -2, '*'))
        self.assertEqual('abc', strlib.rpad('abc', -1, '*'))
        self.assertEqual('abc', strlib.rpad('abc', 0, '*'))
        self.assertEqual('abc', strlib.rpad('abc', 1, '*'))
        self.assertEqual('abc', strlib.rpad('abc', 2, '*'))
        self.assertEqual('abc', strlib.rpad('abc', 3, '*'))
        self.assertEqual('abc*', strlib.rpad('abc', 4, '*'))
        self.assertEqual('abc**', strlib.rpad('abc', 5, '*'))

        self.assertEqual('abc', strlib.rpad('abc', -2, 'yz'))
        self.assertEqual('abc', strlib.rpad('abc', -1, 'yz'))
        self.assertEqual('abc', strlib.rpad('abc', 0, 'yz'))
        self.assertEqual('abc', strlib.rpad('abc', 1, 'yz'))
        self.assertEqual('abc', strlib.rpad('abc', 2, 'yz'))
        self.assertEqual('abc', strlib.rpad('abc', 3, 'yz'))
        self.assertEqual('abcy', strlib.rpad('abc', 4, 'yz'))
        self.assertEqual('abcyz', strlib.rpad('abc', 5, 'yz'))
        self.assertEqual('abcyzy', strlib.rpad('abc', 6, 'yz'))
        self.assertEqual('abcyzyz', strlib.rpad('abc', 7, 'yz'))
        self.assertEqual('abcyzyzy', strlib.rpad('abc', 8, 'yz'))

        # 12345678
        # abc*****
        # ***yzyzyz
        # --------
        # abcyzyzy

    # 2.3

    def test_fill(self):

        # fill(None, -n), fill(None, 0), fill(None, n)
        self.assertIsNone(strlib.fill(None, -2))
        self.assertIsNone(strlib.fill(None, -1))
        self.assertIsNone(strlib.fill(None, 0))
        self.assertIsNone(strlib.fill(None, 1))
        self.assertIsNone(strlib.fill(None, 2))

        self.assertIsNone(strlib.fill(None, -2, ''))
        self.assertIsNone(strlib.fill(None, -1, ''))
        self.assertIsNone(strlib.fill(None, 0, ''))
        self.assertIsNone(strlib.fill(None, 1, ''))
        self.assertIsNone(strlib.fill(None, 2, ''))

        self.assertIsNone(strlib.fill(None, -2, ' '))
        self.assertIsNone(strlib.fill(None, -1, ' '))
        self.assertIsNone(strlib.fill(None, 0, ' '))
        self.assertIsNone(strlib.fill(None, 1, ' '))
        self.assertIsNone(strlib.fill(None, 2, ' '))

        self.assertIsNone(strlib.fill(None, -2, '*'))
        self.assertIsNone(strlib.fill(None, -1, '*'))
        self.assertIsNone(strlib.fill(None, 0, '*'))
        self.assertIsNone(strlib.fill(None, 1, '*'))
        self.assertIsNone(strlib.fill(None, 2, '*'))

        # fill(empty, -n), fill(empty, 0), fill(empty, n)
        self.assertEqual('', strlib.fill('', -2))
        self.assertEqual('', strlib.fill('', -1))
        self.assertEqual('', strlib.fill('', 0))
        self.assertEqual(' ', strlib.fill('', 1))
        self.assertEqual('  ', strlib.fill('', 2))

        # fill(empty, -n, ''), fill(empty, 0, ''), fill(empty, n, '')
        self.assertEqual('', strlib.fill('', -2, ''))
        self.assertEqual('', strlib.fill('', -1, ''))
        self.assertEqual('', strlib.fill('', 0, ''))
        self.assertEqual('', strlib.fill('', 1, ''))
        self.assertEqual('', strlib.fill('', 2, ''))

        # fill(empty, -n, ' '), fill(empty, 0, ' '), fill(empty, n, ' ')
        self.assertEqual('', strlib.fill('', -2, ' '))
        self.assertEqual('', strlib.fill('', -1, ' '))
        self.assertEqual('', strlib.fill('', 0, ' '))
        self.assertEqual(' ', strlib.fill('', 1, ' '))
        self.assertEqual('  ', strlib.fill('', 2, ' '))

        # fill(empty, -n, '*'), fill(empty, 0, '*'), fill(empty, n, '*')
        self.assertEqual('', strlib.fill('', -2, '*'))
        self.assertEqual('', strlib.fill('', -1, '*'))
        self.assertEqual('', strlib.fill('', 0, '*'))
        self.assertEqual('*', strlib.fill('', 1, '*'))
        self.assertEqual('**', strlib.fill('', 2, '*'))

        # char

        # fill(char, -n), fill(char, 0), fill(char, n)
        self.assertEqual('', strlib.fill('a', -2))
        self.assertEqual('', strlib.fill('a', -1))
        self.assertEqual('', strlib.fill('a', 0))
        self.assertEqual('a', strlib.fill('a', 1))
        self.assertEqual('a ', strlib.fill('a', 2))
        self.assertEqual('a  ', strlib.fill('a', 3))
        self.assertEqual('a   ', strlib.fill('a', 4))
        self.assertEqual('a    ', strlib.fill('a', 5))

        self.assertEqual('', strlib.fill('a', -2, '*'))
        self.assertEqual('', strlib.fill('a', -1, '*'))
        self.assertEqual('', strlib.fill('a', 0, '*'))
        self.assertEqual('a', strlib.fill('a', 1, '*'))
        self.assertEqual('a*', strlib.fill('a', 2, '*'))
        self.assertEqual('a**', strlib.fill('a', 3, '*'))
        self.assertEqual('a***', strlib.fill('a', 4, '*'))
        self.assertEqual('a****', strlib.fill('a', 5, '*'))

        # string

        # fill(str, -n), fill(str, 0), fill(str, n)
        self.assertEqual('', strlib.fill('abcxyz', -2))
        self.assertEqual('', strlib.fill('abcxyz', -1))
        self.assertEqual('', strlib.fill('abcxyz', 0))
        self.assertEqual('a', strlib.fill('abcxyz', 1))
        self.assertEqual('ab', strlib.fill('abcxyz', 2))
        self.assertEqual('abc', strlib.fill('abcxyz', 3))
        self.assertEqual('a...', strlib.fill('abcxyz', 4))   # ellipsis
        self.assertEqual('ab...', strlib.fill('abcxyz', 5))  # ellipsis
        self.assertEqual('abcxyz', strlib.fill('abcxyz', 6))
        self.assertEqual('abcxyz ', strlib.fill('abcxyz', 7))
        self.assertEqual('abcxyz  ', strlib.fill('abcxyz', 8))

        # fill(str, -n, '*'), fill(str, 0, '*'), fill(str, n, '*')
        self.assertEqual('', strlib.fill('abcxyz', -2, '*'))
        self.assertEqual('', strlib.fill('abcxyz', -1, '*'))
        self.assertEqual('', strlib.fill('abcxyz', 0, '*'))
        self.assertEqual('a', strlib.fill('abcxyz', 1, '*'))
        self.assertEqual('ab', strlib.fill('abcxyz', 2, '*'))
        self.assertEqual('abc', strlib.fill('abcxyz', 3, '*'))
        self.assertEqual('a...', strlib.fill('abcxyz', 4, '*'))   # ellipsis
        self.assertEqual('ab...', strlib.fill('abcxyz', 5, '*'))  # ellipsis
        self.assertEqual('abcxyz', strlib.fill('abcxyz', 6, '*'))
        self.assertEqual('abcxyz*', strlib.fill('abcxyz', 7, '*'))
        self.assertEqual('abcxyz**', strlib.fill('abcxyz', 8, '*'))


    def test_ellipsis(self):

        # ellipsis(None)
        self.assertIsNone(strlib.ellipsis(None, -2))
        self.assertIsNone(strlib.ellipsis(None, -1))
        self.assertIsNone(strlib.ellipsis(None, 0))
        self.assertIsNone(strlib.ellipsis(None, 1))
        self.assertIsNone(strlib.ellipsis(None, 2))

        # ellipsis(empty, -n), ellipsis(empty, 0), ellipsis(empty, n)
        self.assertEqual('', strlib.ellipsis('', -2))
        self.assertEqual('', strlib.ellipsis('', -1))
        self.assertEqual('', strlib.ellipsis('', 0))
        self.assertEqual('', strlib.ellipsis('', 1))
        self.assertEqual('', strlib.ellipsis('', 2))

        # char

        # ellipsis(char, -n), ellipsis(char, 0), ellipsis(char, n)
        self.assertEqual('a', strlib.ellipsis('a', -2))
        self.assertEqual('a', strlib.ellipsis('a', -1))
        self.assertEqual('a', strlib.ellipsis('a', 0))
        self.assertEqual('a', strlib.ellipsis('a', 1))
        self.assertEqual('a', strlib.ellipsis('a', 2))

        # string

        # ellipsis(str, -n), ellipsis(str, 0), ellipsis(str, n)
        self.assertEqual('abcxyz', strlib.ellipsis('abcxyz', -2))
        self.assertEqual('abcxyz', strlib.ellipsis('abcxyz', -1))
        self.assertEqual('abcxyz', strlib.ellipsis('abcxyz', 0))
        self.assertEqual('a', strlib.ellipsis('abcxyz', 1))
        self.assertEqual('ab', strlib.ellipsis('abcxyz', 2))
        self.assertEqual('abc', strlib.ellipsis('abcxyz', 3))
        self.assertEqual('a...', strlib.ellipsis('abcxyz', 4))   # ellipsis
        self.assertEqual('ab...', strlib.ellipsis('abcxyz', 5))  # ellipsis
        self.assertEqual('abcxyz', strlib.ellipsis('abcxyz', 6))
        self.assertEqual('abcxyz', strlib.ellipsis('abcxyz', 7))
        self.assertEqual('abcxyz', strlib.ellipsis('abcxyz', 8))

    def test_trunc(self):

        # trunc(None)
        self.assertIsNone(strlib.trunc(None, -2))
        self.assertIsNone(strlib.trunc(None, -1))
        self.assertIsNone(strlib.trunc(None, 0))
        self.assertIsNone(strlib.trunc(None, 1))
        self.assertIsNone(strlib.trunc(None, 2))

        # ellipsis=False
        self.assertIsNone(strlib.trunc(None, -2, False))
        self.assertIsNone(strlib.trunc(None, -1, False))
        self.assertIsNone(strlib.trunc(None, 0, False))
        self.assertIsNone(strlib.trunc(None, 1, False))
        self.assertIsNone(strlib.trunc(None, 2, False))

        # ellipsis=True
        self.assertIsNone(strlib.trunc(None, -2, True))
        self.assertIsNone(strlib.trunc(None, -1, True))
        self.assertIsNone(strlib.trunc(None, 0, True))
        self.assertIsNone(strlib.trunc(None, 1, True))
        self.assertIsNone(strlib.trunc(None, 2, True))

        # trunc(empty, -n), trunc(empty, 0), trunc(empty, n)
        self.assertEqual('', strlib.trunc('', -2))
        self.assertEqual('', strlib.trunc('', -1))
        self.assertEqual('', strlib.trunc('', 0))
        self.assertEqual('', strlib.trunc('', 1))
        self.assertEqual('', strlib.trunc('', 2))

        # ellipsis=False
        self.assertEqual('', strlib.trunc('', -2, False))
        self.assertEqual('', strlib.trunc('', -1, False))
        self.assertEqual('', strlib.trunc('', 0, False))
        self.assertEqual('', strlib.trunc('', 1, False))
        self.assertEqual('', strlib.trunc('', 2, False))

        # ellipsis=True
        self.assertEqual('', strlib.trunc('', -2, True))
        self.assertEqual('', strlib.trunc('', -1, True))
        self.assertEqual('', strlib.trunc('', 0, True))
        self.assertEqual('', strlib.trunc('', 1, True))
        self.assertEqual('', strlib.trunc('', 2, True))

        # char

        # trunc(char, -n), trunc(char, 0), trunc(char, n)
        self.assertEqual('a', strlib.trunc('a', -2))
        self.assertEqual('a', strlib.trunc('a', -1))
        self.assertEqual('a', strlib.trunc('a', 0))
        self.assertEqual('a', strlib.trunc('a', 1))
        self.assertEqual('a', strlib.trunc('a', 2))

        # ellipsis=False
        self.assertEqual('a', strlib.trunc('a', -2, False))
        self.assertEqual('a', strlib.trunc('a', -1, False))
        self.assertEqual('a', strlib.trunc('a', 0, False))
        self.assertEqual('a', strlib.trunc('a', 1, False))
        self.assertEqual('a', strlib.trunc('a', 2, False))

        # ellipsis=True
        self.assertEqual('a', strlib.trunc('a', -2, True))
        self.assertEqual('a', strlib.trunc('a', -1, True))
        self.assertEqual('a', strlib.trunc('a', 0, True))
        self.assertEqual('a', strlib.trunc('a', 1, True))
        self.assertEqual('a', strlib.trunc('a', 2, True))

        # string

        # trunc(str, -n), trunc(str, 0), trunc(str, n)
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', -2))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', -1))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 0))
        self.assertEqual('a', strlib.trunc('abcxyz', 1))
        self.assertEqual('ab', strlib.trunc('abcxyz', 2))
        self.assertEqual('abc', strlib.trunc('abcxyz', 3))        
        self.assertEqual('abcx', strlib.trunc('abcxyz', 4))   # non ellipsis
        self.assertEqual('abcxy', strlib.trunc('abcxyz', 5))  # non ellipsis
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 6))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 7))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 8))

        # ellipsis=False
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', -2, False))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', -1, False))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 0, False))
        self.assertEqual('a', strlib.trunc('abcxyz', 1, False))
        self.assertEqual('ab', strlib.trunc('abcxyz', 2, False))
        self.assertEqual('abc', strlib.trunc('abcxyz', 3, False))
        self.assertEqual('abcx', strlib.trunc('abcxyz', 4, False))   # non ellipsis
        self.assertEqual('abcxy', strlib.trunc('abcxyz', 5, False))  # non ellipsis
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 6, False))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 7, False))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 8, False))

        # ellipsis=True
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', -2, True))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', -1, True))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 0, True))
        self.assertEqual('a', strlib.trunc('abcxyz', 1, True))
        self.assertEqual('ab', strlib.trunc('abcxyz', 2, True))
        self.assertEqual('abc', strlib.trunc('abcxyz', 3, True))        
        self.assertEqual('a...', strlib.trunc('abcxyz', 4, True))   # ellipsis
        self.assertEqual('ab...', strlib.trunc('abcxyz', 5, True))  # ellipsis
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 6, True))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 7, True))
        self.assertEqual('abcxyz', strlib.trunc('abcxyz', 8, True))

    def test_left(self):

        # left(None)
        self.assertIsNone(strlib.left(None, -2))
        self.assertIsNone(strlib.left(None, -1))
        self.assertIsNone(strlib.left(None, 0))
        self.assertIsNone(strlib.left(None, 1))
        self.assertIsNone(strlib.left(None, 2))

        # left(empty, -n), left(empty, 0), left(empty, n)
        self.assertEqual('', strlib.left('', -2))
        self.assertEqual('', strlib.left('', -1))
        self.assertEqual('', strlib.left('', 0))
        self.assertEqual('', strlib.left('', 1))
        self.assertEqual('', strlib.left('', 2))

        # char

        # left(char, -n), left(char, 0), left(char, n)
        self.assertEqual('', strlib.left('a', -2))
        self.assertEqual('', strlib.left('a', -1))
        self.assertEqual('', strlib.left('a', 0))
        self.assertEqual('a', strlib.left('a', 1))
        self.assertEqual('a', strlib.left('a', 2))

        # string

        # left(str, -n), left(str, 0), left(str, n)
        self.assertEqual('', strlib.left('abcxyz', -2))
        self.assertEqual('', strlib.left('abcxyz', -1))
        self.assertEqual('', strlib.left('abcxyz', 0))
        self.assertEqual('a', strlib.left('abcxyz', 1))
        self.assertEqual('ab', strlib.left('abcxyz', 2))
        self.assertEqual('abc', strlib.left('abcxyz', 3))        
        self.assertEqual('abcx', strlib.left('abcxyz', 4))
        self.assertEqual('abcxy', strlib.left('abcxyz', 5))
        self.assertEqual('abcxyz', strlib.left('abcxyz', 6))
        self.assertEqual('abcxyz', strlib.left('abcxyz', 7))
        self.assertEqual('abcxyz', strlib.left('abcxyz', 8))

    def test_right(self):

        # right(None)
        self.assertIsNone(strlib.right(None, -2))
        self.assertIsNone(strlib.right(None, -1))
        self.assertIsNone(strlib.right(None, 0))
        self.assertIsNone(strlib.right(None, 1))
        self.assertIsNone(strlib.right(None, 2))

        # right(empty, -n), right(empty, 0), right(empty, n)
        self.assertEqual('', strlib.right('', -2))
        self.assertEqual('', strlib.right('', -1))
        self.assertEqual('', strlib.right('', 0))
        self.assertEqual('', strlib.right('', 1))
        self.assertEqual('', strlib.right('', 2))

        # char

        # right(char, -n), right(char, 0), right(char, n)
        self.assertEqual('', strlib.right('a', -2))
        self.assertEqual('', strlib.right('a', -1))
        self.assertEqual('', strlib.right('a', 0))
        self.assertEqual('a', strlib.right('a', 1))
        self.assertEqual('a', strlib.right('a', 2))

        # string

        # right(str, -n), right(str, 0), right(str, n)
        self.assertEqual('', strlib.right('abcxyz', -2))
        self.assertEqual('', strlib.right('abcxyz', -1))
        self.assertEqual('', strlib.right('abcxyz', 0))
        self.assertEqual('z', strlib.right('abcxyz', 1))
        self.assertEqual('yz', strlib.right('abcxyz', 2))
        self.assertEqual('xyz', strlib.right('abcxyz', 3))        
        self.assertEqual('cxyz', strlib.right('abcxyz', 4))
        self.assertEqual('bcxyz', strlib.right('abcxyz', 5))
        self.assertEqual('abcxyz', strlib.right('abcxyz', 6))
        self.assertEqual('abcxyz', strlib.right('abcxyz', 7))
        self.assertEqual('abcxyz', strlib.right('abcxyz', 8))

    # 3.1

    def test_capitalize(self):

        # capitalize(None), capitalize(empty)
        self.assertIsNone(strlib.capitalize(None))
        self.assertEqual('', strlib.capitalize(''))

        self.assertEqual(' ', strlib.capitalize(' '))
        self.assertEqual('  ', strlib.capitalize('  '))

        self.assertEqual('A', strlib.capitalize('a'))
        self.assertEqual('Ab', strlib.capitalize('ab'))
        self.assertEqual('Abc', strlib.capitalize('abc'))
        self.assertEqual('Abcd', strlib.capitalize('abcd'))

        # ForceRest=default
        self.assertEqual('AB', strlib.capitalize('aB'))
        self.assertEqual('ABc', strlib.capitalize('aBc'))
        self.assertEqual('ABcd', strlib.capitalize('aBcd'))

        self.assertEqual('Hello world!', strlib.capitalize('Hello world!'))
        self.assertEqual('Hello world!', strlib.capitalize('hello world!'))

        # ForceRest=False
        self.assertEqual('AB', strlib.capitalize('aB', False))
        self.assertEqual('ABc', strlib.capitalize('aBc', False))
        self.assertEqual('ABcd', strlib.capitalize('aBcd', False))

        self.assertEqual('Hello world!', strlib.capitalize('Hello world!', False))
        self.assertEqual('Hello world!', strlib.capitalize('hello world!', False))

        # ForceRest=True
        self.assertEqual('Ab', strlib.capitalize('aB', True))
        self.assertEqual('Abc', strlib.capitalize('aBc', True))
        self.assertEqual('Abcd', strlib.capitalize('aBcd', True))

        self.assertEqual('Hello world!', strlib.capitalize('Hello world!', True))
        self.assertEqual('Hello world!', strlib.capitalize('hello world!', True))

        ##
        self.assertEqual('Hello world!', strlib.capitalize('Hello World!', True))
        self.assertEqual('Hello world!', strlib.capitalize('hello World!', True))

    def test_decapitalize(self):

        # decapitalize(None), decapitalize(empty)
        self.assertIsNone(strlib.decapitalize(None))
        self.assertEqual('', strlib.decapitalize(''))

        self.assertEqual(' ', strlib.decapitalize(' '))
        self.assertEqual('  ', strlib.decapitalize('  '))

        self.assertEqual('a', strlib.decapitalize('A'))
        self.assertEqual('ab', strlib.decapitalize('Ab'))
        self.assertEqual('abc', strlib.decapitalize('Abc'))
        self.assertEqual('abcd', strlib.decapitalize('Abcd'))

        # ForceRest=default
        self.assertEqual('ab', strlib.decapitalize('Ab'))
        self.assertEqual('abC', strlib.decapitalize('AbC'))
        self.assertEqual('abCd', strlib.decapitalize('AbCd'))

        self.assertEqual('hello world!', strlib.decapitalize('hello world!'))
        self.assertEqual('hello world!', strlib.decapitalize('Hello world!'))

        # ForceRest=False
        self.assertEqual('ab', strlib.decapitalize('Ab', False))
        self.assertEqual('abC', strlib.decapitalize('AbC', False))
        self.assertEqual('abCd', strlib.decapitalize('AbCd', False))

        self.assertEqual('hello world!', strlib.decapitalize('hello world!', False))
        self.assertEqual('hello world!', strlib.decapitalize('Hello world!', False))

        # ForceRest=True
        self.assertEqual('aB', strlib.decapitalize('Ab', True))
        self.assertEqual('aBC', strlib.decapitalize('AbC', True))
        self.assertEqual('aBCD', strlib.decapitalize('AbCd', True))

        ##
        self.assertEqual('hELLO WORLD!', strlib.decapitalize('hello world!', True))
        self.assertEqual('hELLO WORLD!', strlib.decapitalize('Hello world!', True))        

    ####

    def test_upper(self): # alias: toUpperCase(str)

        # upper(None), upper(empty)
        self.assertIsNone(strlib.upper(None))
        self.assertEqual('', strlib.upper(''))

        # upper(blank)
        self.assertEqual(' ', strlib.upper(' '))
        self.assertEqual('  ', strlib.upper('  '))

        self.assertEqual('0123456789.,:!?', strlib.upper('0123456789.,:!?'))

        # upper(value)
        self.assertEqual('A', strlib.upper('A'))
        self.assertEqual('AB', strlib.upper('AB'))
        self.assertEqual('ABC', strlib.upper('ABC'))
        self.assertEqual('ABCD', strlib.upper('ABCD'))

        self.assertEqual('A', strlib.upper('a'))
        self.assertEqual('AB', strlib.upper('ab'))
        self.assertEqual('ABC', strlib.upper('abc'))
        self.assertEqual('ABCD', strlib.upper('abcd'))

        self.assertEqual('AB', strlib.upper('aB'))
        self.assertEqual('ABC', strlib.upper('aBc'))
        self.assertEqual('ABCD', strlib.upper('aBcD'))

    def test_lower(self): # alias: toLowercase(str)

        # lower(None), lower(empty)
        self.assertIsNone(strlib.lower(None))
        self.assertEqual('', strlib.lower(''))

        # lower(blank)
        self.assertEqual(' ', strlib.lower(' '))
        self.assertEqual('  ', strlib.lower('  '))

        self.assertEqual('0123456789.,:!?', strlib.lower('0123456789.,:!?'))

        # lower(value)
        self.assertEqual('a', strlib.lower('a'))
        self.assertEqual('ab', strlib.lower('ab'))
        self.assertEqual('abc', strlib.lower('abc'))
        self.assertEqual('abcd', strlib.lower('abcd'))

        self.assertEqual('a', strlib.lower('A'))
        self.assertEqual('ab', strlib.lower('AB'))
        self.assertEqual('abc', strlib.lower('ABC'))
        self.assertEqual('abcd', strlib.lower('ABCD'))

        self.assertEqual('ab', strlib.lower('Ab'))
        self.assertEqual('abc', strlib.lower('AbC'))
        self.assertEqual('abcd', strlib.lower('AbCd'))

    def test_toUpperCase(self):

        # toUpperCase(None), toUpperCase(empty)
        self.assertIsNone(strlib.toUpperCase(None))
        self.assertEqual('', strlib.toUpperCase(''))

        # toUpperCase(blank)
        self.assertEqual(' ', strlib.toUpperCase(' '))
        self.assertEqual('  ', strlib.toUpperCase('  '))

        self.assertEqual('0123456789.,:!?', strlib.toUpperCase('0123456789.,:!?'))

        # toUpperCase(value)
        self.assertEqual('A', strlib.toUpperCase('A'))
        self.assertEqual('AB', strlib.toUpperCase('AB'))
        self.assertEqual('ABC', strlib.toUpperCase('ABC'))
        self.assertEqual('ABCD', strlib.toUpperCase('ABCD'))

        self.assertEqual('A', strlib.toUpperCase('a'))
        self.assertEqual('AB', strlib.toUpperCase('ab'))
        self.assertEqual('ABC', strlib.toUpperCase('abc'))
        self.assertEqual('ABCD', strlib.toUpperCase('abcd'))

        self.assertEqual('AB', strlib.toUpperCase('aB'))
        self.assertEqual('ABC', strlib.toUpperCase('aBc'))
        self.assertEqual('ABCD', strlib.toUpperCase('aBcD'))

    def test_toLowerCase(self):

        # toLowerCase(None), toLowerCase(empty)
        self.assertIsNone(strlib.toLowerCase(None))
        self.assertEqual('', strlib.toLowerCase(''))

        # toLowerCase(blank)
        self.assertEqual(' ', strlib.toLowerCase(' '))
        self.assertEqual('  ', strlib.toLowerCase('  '))

        self.assertEqual('0123456789.,:!?', strlib.toLowerCase('0123456789.,:!?'))

        # toLowerCase(value)
        self.assertEqual('a', strlib.toLowerCase('a'))
        self.assertEqual('ab', strlib.toLowerCase('ab'))
        self.assertEqual('abc', strlib.toLowerCase('abc'))
        self.assertEqual('abcd', strlib.toLowerCase('abcd'))

        self.assertEqual('a', strlib.toLowerCase('A'))
        self.assertEqual('ab', strlib.toLowerCase('AB'))
        self.assertEqual('abc', strlib.toLowerCase('ABC'))
        self.assertEqual('abcd', strlib.toLowerCase('ABCD'))

        self.assertEqual('ab', strlib.toLowerCase('Ab'))
        self.assertEqual('abc', strlib.toLowerCase('AbC'))
        self.assertEqual('abcd', strlib.toLowerCase('AbCd'))

    def test_toCase(self):

        #### Upper ####

        # toCase(None), toCase(empty, True)
        self.assertIsNone(strlib.toCase(None, True))
        self.assertEqual('', strlib.toCase('', True))

        # toCase(blank, True)
        self.assertEqual(' ', strlib.toCase(' ', True))
        self.assertEqual('  ', strlib.toCase('  ', True))

        self.assertEqual('0123456789.,:!?', strlib.toCase('0123456789.,:!?', True))

        # toCase(value, True)
        self.assertEqual('A', strlib.toCase('A', True))
        self.assertEqual('AB', strlib.toCase('AB', True))
        self.assertEqual('ABC', strlib.toCase('ABC', True))
        self.assertEqual('ABCD', strlib.toCase('ABCD', True))

        self.assertEqual('A', strlib.toCase('a', True))
        self.assertEqual('AB', strlib.toCase('ab', True))
        self.assertEqual('ABC', strlib.toCase('abc', True))
        self.assertEqual('ABCD', strlib.toCase('abcd', True))

        self.assertEqual('AB', strlib.toCase('aB', True))
        self.assertEqual('ABC', strlib.toCase('aBc', True))
        self.assertEqual('ABCD', strlib.toCase('aBcD', True))

        #### Lower ####

        # toCase(None, False), toCase(empty, False)
        self.assertIsNone(strlib.toCase(None, False))
        self.assertEqual('', strlib.toCase('', False))

        # toCase(blank, False)
        self.assertEqual(' ', strlib.toCase(' ', False))
        self.assertEqual('  ', strlib.toCase('  ', False))

        self.assertEqual('0123456789.,:!?', strlib.toCase('0123456789.,:!?', False))

        # toCase(value, False)
        self.assertEqual('a', strlib.toCase('a', False))
        self.assertEqual('ab', strlib.toCase('ab', False))
        self.assertEqual('abc', strlib.toCase('abc', False))
        self.assertEqual('abcd', strlib.toCase('abcd', False))

        self.assertEqual('a', strlib.toCase('A', False))
        self.assertEqual('ab', strlib.toCase('AB', False))
        self.assertEqual('abc', strlib.toCase('ABC', False))
        self.assertEqual('abcd', strlib.toCase('ABCD', False))

        self.assertEqual('ab', strlib.toCase('Ab', False))
        self.assertEqual('abc', strlib.toCase('AbC', False))
        self.assertEqual('abcd', strlib.toCase('AbCd', False))

    ####

    def test_getCaseCode(self):

        # getCaseCode(None), getCaseCode(empty)
        self.assertEqual(strlib.CT_NONE, strlib.getCaseCode(None))
        self.assertEqual(strlib.CT_NONE, strlib.getCaseCode(''))
        self.assertEqual(strlib.CT_NONE, strlib.getCaseCode(' '))
        self.assertEqual(strlib.CT_NONE, strlib.getCaseCode(' '))

        # getCaseCode(unknown)
        self.assertEqual(strlib.CT_NONE, strlib.getCaseCode('blahblahblah'))
        self.assertEqual(strlib.CT_NONE, strlib.getCaseCode('0123456789'))

        # lowercase
        self.assertEqual(strlib.CT_lowercase, strlib.getCaseCode('lower'))

        # UPPERCASE
        self.assertEqual(strlib.CT_UPPERCASE, strlib.getCaseCode('upper'))

        # camelCase
        self.assertEqual(strlib.CT_camelCase, strlib.getCaseCode('camel'))

        # PascalCase
        self.assertEqual(strlib.CT_PascalCase, strlib.getCaseCode('Camel'))
        self.assertEqual(strlib.CT_PascalCase, strlib.getCaseCode('Pascal'))
        self.assertEqual(strlib.CT_PascalCase, strlib.getCaseCode('pascal'))

        # snake_case
        self.assertEqual(strlib.CT_snake_case, strlib.getCaseCode('snake'))

        # SNAKE_CASE
        self.assertEqual(strlib.CT_SNAKE_CASE, strlib.getCaseCode('SNAKE'))
        self.assertEqual(strlib.CT_SNAKE_CASE, strlib.getCaseCode('MACRO'))
        self.assertEqual(strlib.CT_SNAKE_CASE, strlib.getCaseCode('macro'))

        # kebab-case
        self.assertEqual(strlib.CT_kebab_case, strlib.getCaseCode('kebab'))
        self.assertEqual(strlib.CT_kebab_case, strlib.getCaseCode('dash'))
        self.assertEqual(strlib.CT_kebab_case, strlib.getCaseCode('train'))
        self.assertEqual(strlib.CT_kebab_case, strlib.getCaseCode('lisp'))

        # KEBAB-CASE
        self.assertEqual(strlib.CT_KEBAB_CASE, strlib.getCaseCode('KEBAB'))
        self.assertEqual(strlib.CT_KEBAB_CASE, strlib.getCaseCode('DASH'))
        self.assertEqual(strlib.CT_KEBAB_CASE, strlib.getCaseCode('TRAIN'))
        self.assertEqual(strlib.CT_KEBAB_CASE, strlib.getCaseCode('COBOL'))
        self.assertEqual(strlib.CT_KEBAB_CASE, strlib.getCaseCode('cobol'))

        # CT_Kebab_Case
        self.assertEqual(strlib.CT_Kebab_Case, strlib.getCaseCode('Kebab'))
        self.assertEqual(strlib.CT_Kebab_Case, strlib.getCaseCode('Dash'))
        self.assertEqual(strlib.CT_Kebab_Case, strlib.getCaseCode('Train'))

    def test_toCamelCase(self):
        
        # toCamelCase(None), toCamelCase(empty)
        self.assertIsNone(strlib.toCamelCase(None))
        self.assertEqual('', strlib.toCamelCase(''))
        self.assertEqual(' ', strlib.toCamelCase(' '))
        self.assertEqual('  ', strlib.toCamelCase('  '))

        # capitalize = default
        self.assertEqual('ProductName', strlib.toCamelCase('product name'))
        self.assertEqual('ProductName', strlib.toCamelCase('product-name'))
        self.assertEqual('ProductName', strlib.toCamelCase('product_name'))
        self.assertEqual('ProductName', strlib.toCamelCase('productName'))
        
        self.assertEqual('ProductName', strlib.toCamelCase('Product Name'))
        self.assertEqual('ProductName', strlib.toCamelCase('Product-Name'))
        self.assertEqual('ProductName', strlib.toCamelCase('Product_Name'))
        self.assertEqual('ProductName', strlib.toCamelCase('ProductName'))
        
        self.assertEqual('ProductFullName', strlib.toCamelCase('product full name'))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-full-name'))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product_full_name'))
        
        self.assertEqual('ProductFullName', strlib.toCamelCase('product Full name'))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-Full-name'))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product_Full_name'))
        
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-_ full -_name'))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-_ Full -_name'))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-_ FullName'))

        # capitalize = True
        self.assertEqual('ProductName', strlib.toCamelCase('product name', capitalize = True))
        self.assertEqual('ProductName', strlib.toCamelCase('product-name', capitalize = True))
        self.assertEqual('ProductName', strlib.toCamelCase('product_name', capitalize = True))
        self.assertEqual('ProductName', strlib.toCamelCase('productName', capitalize = True))
        
        self.assertEqual('ProductName', strlib.toCamelCase('Product Name', capitalize = True))
        self.assertEqual('ProductName', strlib.toCamelCase('Product-Name', capitalize = True))
        self.assertEqual('ProductName', strlib.toCamelCase('Product_Name', capitalize = True))
        self.assertEqual('ProductName', strlib.toCamelCase('ProductName', capitalize = True))
        
        self.assertEqual('ProductFullName', strlib.toCamelCase('product full name', capitalize = True))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-full-name', capitalize = True))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product_full_name', capitalize = True))
        
        self.assertEqual('ProductFullName', strlib.toCamelCase('product Full name', capitalize = True))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-Full-name', capitalize = True))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product_Full_name', capitalize = True))
        
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-_ full -_name', capitalize = True))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-_ Full -_name', capitalize = True))
        self.assertEqual('ProductFullName', strlib.toCamelCase('product-_ FullName', capitalize = True))

        # capitalize = False
        self.assertEqual('productName', strlib.toCamelCase('product name', capitalize = False))
        self.assertEqual('productName', strlib.toCamelCase('product-name', capitalize = False))
        self.assertEqual('productName', strlib.toCamelCase('product_name', capitalize = False))
        self.assertEqual('productName', strlib.toCamelCase('productName', capitalize = False))
        
        self.assertEqual('productName', strlib.toCamelCase('Product Name', capitalize = False))
        self.assertEqual('productName', strlib.toCamelCase('Product-Name', capitalize = False))
        self.assertEqual('productName', strlib.toCamelCase('Product_Name', capitalize = False))
        self.assertEqual('productName', strlib.toCamelCase('ProductName', capitalize = False))
        
        self.assertEqual('productFullName', strlib.toCamelCase('product full name', capitalize = False))
        self.assertEqual('productFullName', strlib.toCamelCase('product-full-name', capitalize = False))
        self.assertEqual('productFullName', strlib.toCamelCase('product_full_name', capitalize = False))
        
        self.assertEqual('productFullName', strlib.toCamelCase('product Full name', capitalize = False))
        self.assertEqual('productFullName', strlib.toCamelCase('product-Full-name', capitalize = False))
        self.assertEqual('productFullName', strlib.toCamelCase('product_Full_name', capitalize = False))
        
        self.assertEqual('productFullName', strlib.toCamelCase('product-_ full -_name', capitalize = False))
        self.assertEqual('productFullName', strlib.toCamelCase('product-_ Full -_name', capitalize = False))
        self.assertEqual('productFullName', strlib.toCamelCase('product-_ FullName', capitalize = False))

    def test_toSnakeCase(self):
        
        # toSnakeCase(None), toSnakeCase(empty)
        self.assertIsNone(strlib.toSnakeCase(None))
        self.assertEqual('', strlib.toSnakeCase(''))
        self.assertEqual(' ', strlib.toSnakeCase(' '))
        self.assertEqual('  ', strlib.toSnakeCase('  '))

        # toSnakeCase(value)        
        self.assertEqual('product_name', strlib.toSnakeCase('product name'))
        self.assertEqual('product_name', strlib.toSnakeCase('product-name'))
        self.assertEqual('product_name', strlib.toSnakeCase('product_name'))        
        self.assertEqual('product_name', strlib.toSnakeCase('ProductName'))

        # upper = False
        self.assertEqual('product_name', strlib.toSnakeCase('product name', upper = False))
        self.assertEqual('product_name', strlib.toSnakeCase('product-name', upper = False))
        self.assertEqual('product_name', strlib.toSnakeCase('product_name', upper = False))        
        self.assertEqual('product_name', strlib.toSnakeCase('ProductName', upper = False))

        # upper = True
        self.assertEqual('PRODUCT_NAME', strlib.toSnakeCase('product name', upper = True))
        self.assertEqual('PRODUCT_NAME', strlib.toSnakeCase('product-name', upper = True))
        self.assertEqual('PRODUCT_NAME', strlib.toSnakeCase('product_name', upper = True))        
        self.assertEqual('PRODUCT_NAME', strlib.toSnakeCase('ProductName', upper = True))


    def test_toKebabCase(self):
        
        # toKebabCase(None), toKebabCase(empty)
        self.assertIsNone(strlib.toKebabCase(None))
        self.assertEqual('', strlib.toKebabCase(''))
        self.assertEqual(' ', strlib.toKebabCase(' '))
        self.assertEqual('  ', strlib.toKebabCase('  '))

        # toKebabCase(value)        
        self.assertEqual('product-name', strlib.toKebabCase('product name'))
        self.assertEqual('product-name', strlib.toKebabCase('product-name'))
        self.assertEqual('product-name', strlib.toKebabCase('product_name'))        
        self.assertEqual('product-name', strlib.toKebabCase('ProductName'))

        # upper = False
        self.assertEqual('product-name', strlib.toKebabCase('product name', upper = False))
        self.assertEqual('product-name', strlib.toKebabCase('product-name', upper = False))
        self.assertEqual('product-name', strlib.toKebabCase('product_name', upper = False))        
        self.assertEqual('product-name', strlib.toKebabCase('ProductName', upper = False))

        # upper = True
        self.assertEqual('PRODUCT-NAME', strlib.toKebabCase('product name', upper = True))
        self.assertEqual('PRODUCT-NAME', strlib.toKebabCase('product-name', upper = True))
        self.assertEqual('PRODUCT-NAME', strlib.toKebabCase('product_name', upper = True))        
        self.assertEqual('PRODUCT-NAME', strlib.toKebabCase('ProductName', upper = True))

    def test_reverse(self):
        
        # reverse(None), reverse(empty)
        self.assertIsNone(strlib.reverse(None))
        self.assertEqual('', strlib.reverse(''))

        # reverse(blank)
        self.assertEqual(' ', strlib.reverse(' '))
        self.assertEqual('  ', strlib.reverse('  '))

        # reverse(value)
        self.assertEqual('*', strlib.reverse('*'))
        self.assertEqual('**', strlib.reverse('**'))
        self.assertEqual('***', strlib.reverse('***'))
        self.assertEqual('****', strlib.reverse('****'))
        self.assertEqual('*****', strlib.reverse('*****'))
        self.assertEqual('******', strlib.reverse('******'))

        self.assertEqual('a', strlib.reverse('a'))
        self.assertEqual('ba', strlib.reverse('ab'))
        self.assertEqual('cba', strlib.reverse('abc'))
        self.assertEqual('dcba', strlib.reverse('abcd'))
        self.assertEqual('edcba', strlib.reverse('abcde'))
        self.assertEqual('fedcba', strlib.reverse('abcdef'))

    # 4.1

    def test_startsWith(self):
        
        # startsWith(None, value)
        self.assertFalse(strlib.startsWith(None, None))
        self.assertFalse(strlib.startsWith(None, ''))
        self.assertFalse(strlib.startsWith(None, ' '))
        self.assertFalse(strlib.startsWith(None, 'abc'))

        # startsWith(empty, value)
        self.assertFalse(strlib.startsWith('', None))
        self.assertFalse(strlib.startsWith('', ''))     # important
        self.assertFalse(strlib.startsWith('', ' '))
        self.assertFalse(strlib.startsWith('', 'abc'))

        # startsWith(blank, value)
        self.assertFalse(strlib.startsWith(' ', None))
        self.assertFalse(strlib.startsWith(' ', ''))    # important
        self.assertTrue(strlib.startsWith(' ', ' '))    # True
        self.assertFalse(strlib.startsWith(' ', 'abc'))

        # startsWith(value, value)

        # False
        self.assertFalse(strlib.startsWith('abc', None))
        self.assertFalse(strlib.startsWith('abc', ''))  # important
        self.assertFalse(strlib.startsWith('abc', ' '))
        self.assertFalse(strlib.startsWith('abc', 'xyz'))

        # True
        self.assertTrue(strlib.startsWith('abc', 'a'))
        self.assertTrue(strlib.startsWith('abc', 'ab'))
        self.assertTrue(strlib.startsWith('abc', 'abc'))

        ##
        self.assertTrue(strlib.startsWith('myfile.txt', 'my'))
        self.assertTrue(strlib.startsWith('myfile.txt', 'myfile'))
        self.assertTrue(strlib.startsWith('myfile.txt', 'myfile.txt'))

    def test_startsWithIgnoreCase(self):
        
        # startsWithIgnoreCase(None, value)
        self.assertFalse(strlib.startsWithIgnoreCase(None, None))
        self.assertFalse(strlib.startsWithIgnoreCase(None, ''))
        self.assertFalse(strlib.startsWithIgnoreCase(None, ' '))
        self.assertFalse(strlib.startsWithIgnoreCase(None, 'abc'))

        # startsWithIgnoreCase(empty, value)
        self.assertFalse(strlib.startsWithIgnoreCase('', None))
        self.assertFalse(strlib.startsWithIgnoreCase('', ''))     # important
        self.assertFalse(strlib.startsWithIgnoreCase('', ' '))
        self.assertFalse(strlib.startsWithIgnoreCase('', 'abc'))

        # startsWithIgnoreCase(blank, value)
        self.assertFalse(strlib.startsWithIgnoreCase(' ', None))
        self.assertFalse(strlib.startsWithIgnoreCase(' ', ''))    # important
        self.assertTrue(strlib.startsWithIgnoreCase(' ', ' '))    # True
        self.assertFalse(strlib.startsWithIgnoreCase(' ', 'abc'))

        # startsWithIgnoreCase(value, value)

        # False
        self.assertFalse(strlib.startsWithIgnoreCase('abc', None))
        self.assertFalse(strlib.startsWithIgnoreCase('abc', ''))  # important
        self.assertFalse(strlib.startsWithIgnoreCase('abc', ' '))
        self.assertFalse(strlib.startsWithIgnoreCase('abc', 'xyz'))

        # True
        self.assertTrue(strlib.startsWithIgnoreCase('abc', 'a'))
        self.assertTrue(strlib.startsWithIgnoreCase('abc', 'ab'))
        self.assertTrue(strlib.startsWithIgnoreCase('abc', 'abc'))

        # True - IgnoreCase
        self.assertTrue(strlib.startsWithIgnoreCase('abc', 'A'))
        self.assertTrue(strlib.startsWithIgnoreCase('abc', 'Ab'))
        self.assertTrue(strlib.startsWithIgnoreCase('abc', 'AbC'))

        ##
        self.assertTrue(strlib.startsWithIgnoreCase('myfile.txt', 'my'))
        self.assertTrue(strlib.startsWithIgnoreCase('myfile.txt', 'myfile'))
        self.assertTrue(strlib.startsWithIgnoreCase('myfile.txt', 'myfile.txt'))

        self.assertTrue(strlib.startsWithIgnoreCase('myfile.txt', 'My'))
        self.assertTrue(strlib.startsWithIgnoreCase('myfile.txt', 'MyFile'))
        self.assertTrue(strlib.startsWithIgnoreCase('myfile.txt', 'MyFile.TxT'))


    def test_endsWith(self):
        
        # endsWith(None, value)
        self.assertFalse(strlib.endsWith(None, None))
        self.assertFalse(strlib.endsWith(None, ''))
        self.assertFalse(strlib.endsWith(None, ' '))
        self.assertFalse(strlib.endsWith(None, 'abc'))

        # endsWith(empty, value)
        self.assertFalse(strlib.endsWith('', None))
        self.assertFalse(strlib.endsWith('', ''))     # important
        self.assertFalse(strlib.endsWith('', ' '))
        self.assertFalse(strlib.endsWith('', 'abc'))

        # endsWith(blank, value)
        self.assertFalse(strlib.endsWith(' ', None))
        self.assertFalse(strlib.endsWith(' ', ''))    # important
        self.assertTrue(strlib.endsWith(' ', ' '))    # True
        self.assertFalse(strlib.endsWith(' ', 'abc'))

        # endsWith(value, value)

        # False
        self.assertFalse(strlib.endsWith('abc', None))
        self.assertFalse(strlib.endsWith('abc', ''))  # important
        self.assertFalse(strlib.endsWith('abc', ' '))
        self.assertFalse(strlib.endsWith('abc', 'xyz'))

        # True
        self.assertTrue(strlib.endsWith('abc', 'c'))
        self.assertTrue(strlib.endsWith('abc', 'bc'))
        self.assertTrue(strlib.endsWith('abc', 'abc'))

        ##
        self.assertTrue(strlib.endsWith('myfile.txt', 'txt'))
        self.assertTrue(strlib.endsWith('myfile.txt', '.txt'))
        self.assertTrue(strlib.endsWith('myfile.txt', 'myfile.txt'))

    def test_endsWithIgnoreCase(self):
        
        # endsWithIgnoreCase(None, value)
        self.assertFalse(strlib.endsWithIgnoreCase(None, None))
        self.assertFalse(strlib.endsWithIgnoreCase(None, ''))
        self.assertFalse(strlib.endsWithIgnoreCase(None, ' '))
        self.assertFalse(strlib.endsWithIgnoreCase(None, 'abc'))

        # endsWithIgnoreCase(empty, value)
        self.assertFalse(strlib.endsWithIgnoreCase('', None))
        self.assertFalse(strlib.endsWithIgnoreCase('', ''))     # important
        self.assertFalse(strlib.endsWithIgnoreCase('', ' '))
        self.assertFalse(strlib.endsWithIgnoreCase('', 'abc'))

        # endsWithIgnoreCase(blank, value)
        self.assertFalse(strlib.endsWithIgnoreCase(' ', None))
        self.assertFalse(strlib.endsWithIgnoreCase(' ', ''))    # important
        self.assertTrue(strlib.endsWithIgnoreCase(' ', ' '))    # True
        self.assertFalse(strlib.endsWithIgnoreCase(' ', 'abc'))

        # endsWithIgnoreCase(value, value)

        # False
        self.assertFalse(strlib.endsWithIgnoreCase('abc', None))
        self.assertFalse(strlib.endsWithIgnoreCase('abc', ''))  # important
        self.assertFalse(strlib.endsWithIgnoreCase('abc', ' '))
        self.assertFalse(strlib.endsWithIgnoreCase('abc', 'xyz'))

        # True
        self.assertTrue(strlib.endsWithIgnoreCase('abc', 'c'))
        self.assertTrue(strlib.endsWithIgnoreCase('abc', 'bc'))
        self.assertTrue(strlib.endsWithIgnoreCase('abc', 'abc'))

        # True - IgnoreCase
        self.assertTrue(strlib.endsWithIgnoreCase('abc', 'C'))
        self.assertTrue(strlib.endsWithIgnoreCase('abc', 'Bc'))
        self.assertTrue(strlib.endsWithIgnoreCase('abc', 'aBc'))

        ##
        self.assertTrue(strlib.endsWithIgnoreCase('myfile.txt', 'txt'))
        self.assertTrue(strlib.endsWithIgnoreCase('myfile.txt', '.txt'))
        self.assertTrue(strlib.endsWithIgnoreCase('myfile.txt', 'myfile.txt'))

        self.assertTrue(strlib.endsWithIgnoreCase('myfile.txt', 'TxT'))
        self.assertTrue(strlib.endsWithIgnoreCase('myfile.txt', '.TxT'))
        self.assertTrue(strlib.endsWithIgnoreCase('myfile.txt', 'MyFile.TxT'))

    def test_hasPrefix(self):

        # hasPrefix(None, value)
        self.assertFalse(strlib.hasPrefix(None, None))
        self.assertFalse(strlib.hasPrefix(None, ''))
        self.assertFalse(strlib.hasPrefix(None, ' '))
        self.assertFalse(strlib.hasPrefix(None, 'abc'))

        # hasPrefix(empty, value)
        self.assertFalse(strlib.hasPrefix('', None))
        self.assertFalse(strlib.hasPrefix('', ''))     # important
        self.assertFalse(strlib.hasPrefix('', ' '))
        self.assertFalse(strlib.hasPrefix('', 'abc'))

        # hasPrefix(blank, value)
        self.assertFalse(strlib.hasPrefix(' ', None))
        self.assertFalse(strlib.hasPrefix(' ', ''))    # important
        self.assertTrue(strlib.hasPrefix(' ', ' '))    # True
        self.assertFalse(strlib.hasPrefix(' ', 'abc'))

        # hasPrefix(value, value)

        # False
        self.assertFalse(strlib.hasPrefix('abc', None))
        self.assertFalse(strlib.hasPrefix('abc', ''))  # important
        self.assertFalse(strlib.hasPrefix('abc', ' '))
        self.assertFalse(strlib.hasPrefix('abc', 'xyz'))

        # True
        self.assertTrue(strlib.hasPrefix('abc', 'a'))
        self.assertTrue(strlib.hasPrefix('abc', 'ab'))
        self.assertTrue(strlib.hasPrefix('abc', 'abc'))

        ##
        self.assertTrue(strlib.hasPrefix('myfile.txt', 'my'))
        self.assertTrue(strlib.hasPrefix('myfile.txt', 'myfile'))
        self.assertTrue(strlib.hasPrefix('myfile.txt', 'myfile.txt'))

    def test_hasSuffix(self):
        
        # hasSuffix(None, value)
        self.assertFalse(strlib.hasSuffix(None, None))
        self.assertFalse(strlib.hasSuffix(None, ''))
        self.assertFalse(strlib.hasSuffix(None, ' '))
        self.assertFalse(strlib.hasSuffix(None, 'abc'))

        # hasSuffix(empty, value)
        self.assertFalse(strlib.hasSuffix('', None))
        self.assertFalse(strlib.hasSuffix('', ''))     # important
        self.assertFalse(strlib.hasSuffix('', ' '))
        self.assertFalse(strlib.hasSuffix('', 'abc'))

        # hasSuffix(blank, value)
        self.assertFalse(strlib.hasSuffix(' ', None))
        self.assertFalse(strlib.hasSuffix(' ', ''))    # important
        self.assertTrue(strlib.hasSuffix(' ', ' '))    # True
        self.assertFalse(strlib.hasSuffix(' ', 'abc'))

        # hasSuffix(value, value)

        # False
        self.assertFalse(strlib.hasSuffix('abc', None))
        self.assertFalse(strlib.hasSuffix('abc', ''))  # important
        self.assertFalse(strlib.hasSuffix('abc', ' '))
        self.assertFalse(strlib.hasSuffix('abc', 'xyz'))

        # True
        self.assertTrue(strlib.hasSuffix('abc', 'c'))
        self.assertTrue(strlib.hasSuffix('abc', 'bc'))
        self.assertTrue(strlib.hasSuffix('abc', 'abc'))

        ##
        self.assertTrue(strlib.hasSuffix('myfile.txt', 'txt'))
        self.assertTrue(strlib.hasSuffix('myfile.txt', '.txt'))
        self.assertTrue(strlib.hasSuffix('myfile.txt', 'myfile.txt'))

    def test_removePrefix(self):

        # removePrefix(None, value)
        self.assertIsNone(strlib.removePrefix(None, None))
        self.assertIsNone(strlib.removePrefix(None, ''))
        self.assertIsNone(strlib.removePrefix(None, ' '))
        self.assertIsNone(strlib.removePrefix(None, 'abc'))

        # removePrefix(empty, value)
        self.assertEqual('', strlib.removePrefix('', None))
        self.assertEqual('', strlib.removePrefix('', ''))
        self.assertEqual('', strlib.removePrefix('', ' '))
        self.assertEqual('', strlib.removePrefix('', 'abc'))

        # removePrefix(blank, value)
        self.assertEqual(' ', strlib.removePrefix(' ', None))
        self.assertEqual(' ', strlib.removePrefix(' ', ''))
        self.assertEqual('', strlib.removePrefix(' ', ' '))  # True
        self.assertEqual(' ', strlib.removePrefix(' ', 'abc'))

        # removePrefix(value, value)

        # False
        self.assertEqual('abc', strlib.removePrefix('abc', None))
        self.assertEqual('abc', strlib.removePrefix('abc', ''))
        self.assertEqual('abc', strlib.removePrefix('abc', ' '))
        self.assertEqual('abc', strlib.removePrefix('abc', 'xyz'))

        # True
        self.assertEqual('bc', strlib.removePrefix('abc', 'a'))
        self.assertEqual('c', strlib.removePrefix('abc', 'ab'))
        self.assertEqual('', strlib.removePrefix('abc', 'abc'))

        ##
        self.assertEqual('file.txt', strlib.removePrefix('myfile.txt', 'my'))
        self.assertEqual('.txt', strlib.removePrefix('myfile.txt', 'myfile'))
        self.assertEqual('', strlib.removePrefix('myfile.txt', 'myfile.txt'))

    def test_removePrefixes(self):

        # removePrefixes(None, value)
        self.assertIsNone(strlib.removePrefixes(None, None))
        self.assertIsNone(strlib.removePrefixes(None, []))
        self.assertIsNone(strlib.removePrefixes(None, ['']))
        self.assertIsNone(strlib.removePrefixes(None, [' ']))
        self.assertIsNone(strlib.removePrefixes(None, ['abc']))

        # removePrefixes(empty, value)
        self.assertEqual('', strlib.removePrefixes('', None))
        self.assertEqual('', strlib.removePrefixes('', []))
        self.assertEqual('', strlib.removePrefixes('', ['']))
        self.assertEqual('', strlib.removePrefixes('', [' ']))
        self.assertEqual('', strlib.removePrefixes('', ['abc']))

        # removePrefixes(blank, value)
        self.assertEqual(' ', strlib.removePrefixes(' ', None))
        self.assertEqual(' ', strlib.removePrefixes(' ', []))
        self.assertEqual(' ', strlib.removePrefixes(' ', ['']))
        self.assertEqual('', strlib.removePrefixes(' ', [' ']))  # True
        self.assertEqual(' ', strlib.removePrefixes(' ', ['abc']))

        # removePrefixes(value, value)

        # False
        self.assertEqual('abc', strlib.removePrefixes('abc', None))
        self.assertEqual('abc', strlib.removePrefixes('abc', []))
        self.assertEqual('abc', strlib.removePrefixes('abc', ['']))
        self.assertEqual('abc', strlib.removePrefixes('abc', [' ']))
        self.assertEqual('abc', strlib.removePrefixes('abc', ['xyz']))
        self.assertEqual('abc', strlib.removePrefixes('abc', ['xyz', 'def']))
        self.assertEqual('abc', strlib.removePrefixes('abc', ['def', 'xyz']))

        # True
        self.assertEqual('bc', strlib.removePrefixes('abc', ['a']))
        self.assertEqual('c', strlib.removePrefixes('abc', ['ab']))
        self.assertEqual('', strlib.removePrefixes('abc', ['abc']))
        self.assertEqual('', strlib.removePrefixes('abc', ['abc', 'xyz']))
        self.assertEqual('', strlib.removePrefixes('abc', ['xyz', 'abc']))

        self.assertEqual('c', strlib.removePrefixes('abc', ['ab', 'abc'])) # first 'ab'

        ##
        self.assertEqual('file.txt', strlib.removePrefixes('myfile.txt', ['my']))
        self.assertEqual('.txt', strlib.removePrefixes('myfile.txt', ['myfile']))
        self.assertEqual('', strlib.removePrefixes('myfile.txt', ['myfile.txt']))

        self.assertEqual('.txt', strlib.removePrefixes('myfile.txt', ['myfile', 'yourfile']))

    def test_removeSuffix(self):

        # removeSuffix(None, value)
        self.assertIsNone(strlib.removeSuffix(None, None))
        self.assertIsNone(strlib.removeSuffix(None, ''))
        self.assertIsNone(strlib.removeSuffix(None, ' '))
        self.assertIsNone(strlib.removeSuffix(None, 'abc'))

        # removeSuffix(empty, value)
        self.assertEqual('', strlib.removeSuffix('', None))
        self.assertEqual('', strlib.removeSuffix('', ''))
        self.assertEqual('', strlib.removeSuffix('', ' '))
        self.assertEqual('', strlib.removeSuffix('', 'abc'))

        # removeSuffix(blank, value)
        self.assertEqual(' ', strlib.removeSuffix(' ', None))
        self.assertEqual(' ', strlib.removeSuffix(' ', ''))
        self.assertEqual('', strlib.removeSuffix(' ', ' '))  # True
        self.assertEqual(' ', strlib.removeSuffix(' ', 'abc'))

        # removeSuffix(value, value)

        # False
        self.assertEqual('abc', strlib.removeSuffix('abc', None))
        self.assertEqual('abc', strlib.removeSuffix('abc', ''))
        self.assertEqual('abc', strlib.removeSuffix('abc', ' '))
        self.assertEqual('abc', strlib.removeSuffix('abc', 'xyz'))

        # True
        self.assertEqual('ab', strlib.removeSuffix('abc', 'c'))
        self.assertEqual('a', strlib.removeSuffix('abc', 'bc'))
        self.assertEqual('', strlib.removeSuffix('abc', 'abc'))

        ##
        self.assertEqual('myfile.', strlib.removeSuffix('myfile.txt', 'txt'))
        self.assertEqual('myfile', strlib.removeSuffix('myfile.txt', '.txt'))
        self.assertEqual('', strlib.removeSuffix('myfile.txt', 'myfile.txt'))

    def test_removeSuffixes(self):

        # removeSuffixes(None, value)
        self.assertIsNone(strlib.removeSuffixes(None, None))
        self.assertIsNone(strlib.removeSuffixes(None, []))
        self.assertIsNone(strlib.removeSuffixes(None, ['']))
        self.assertIsNone(strlib.removeSuffixes(None, [' ']))
        self.assertIsNone(strlib.removeSuffixes(None, ['abc']))

        # removeSuffixes(empty, value)
        self.assertEqual('', strlib.removeSuffixes('', None))
        self.assertEqual('', strlib.removeSuffixes('', []))
        self.assertEqual('', strlib.removeSuffixes('', ['']))
        self.assertEqual('', strlib.removeSuffixes('', [' ']))
        self.assertEqual('', strlib.removeSuffixes('', ['abc']))

        # removeSuffixes(blank, value)
        self.assertEqual(' ', strlib.removeSuffixes(' ', None))
        self.assertEqual(' ', strlib.removeSuffixes(' ', []))
        self.assertEqual(' ', strlib.removeSuffixes(' ', ['']))
        self.assertEqual('', strlib.removeSuffixes(' ', [' ']))  # True
        self.assertEqual(' ', strlib.removeSuffixes(' ', ['abc']))

        # removeSuffixes(value, value)

        # False
        self.assertEqual('abc', strlib.removeSuffixes('abc', None))
        self.assertEqual('abc', strlib.removeSuffixes('abc', []))
        self.assertEqual('abc', strlib.removeSuffixes('abc', ['']))
        self.assertEqual('abc', strlib.removeSuffixes('abc', [' ']))
        self.assertEqual('abc', strlib.removeSuffixes('abc', ['xyz']))
        self.assertEqual('abc', strlib.removeSuffixes('abc', ['xyz', 'def']))
        self.assertEqual('abc', strlib.removeSuffixes('abc', ['def', 'xyz']))

        # True
        self.assertEqual('ab', strlib.removeSuffixes('abc', ['c']))
        self.assertEqual('a', strlib.removeSuffixes('abc', ['bc']))
        self.assertEqual('', strlib.removeSuffixes('abc', ['abc']))
        self.assertEqual('', strlib.removeSuffixes('abc', ['abc', 'xyz']))
        self.assertEqual('', strlib.removeSuffixes('abc', ['xyz', 'abc']))

        self.assertEqual('a', strlib.removeSuffixes('abc', ['bc', 'abc'])) # first 'bc'

        ##
        self.assertEqual('myfile.', strlib.removeSuffixes('myfile.txt', ['txt']))
        self.assertEqual('myfile', strlib.removeSuffixes('myfile.txt', ['.txt']))
        self.assertEqual('', strlib.removeSuffixes('myfile.txt', ['myfile.txt']))

        self.assertEqual('myfile', strlib.removeSuffixes('myfile.txt', ['.txt', '.csv']))

    # 4.3

    def test_isQuoted(self):

        # False
        self.assertFalse(strlib.isQuoted(None))
        self.assertFalse(strlib.isQuoted(''))
        self.assertFalse(strlib.isQuoted(' '))
        self.assertFalse(strlib.isQuoted('abc'))

        # False "
        self.assertFalse(strlib.isQuoted('"'))           # False - only one quote
        self.assertFalse(strlib.isQuoted('"', '"', '"')) # False - only one quote

        # False '
        self.assertFalse(strlib.isQuoted("'"))           # False - only one quote
        self.assertFalse(strlib.isQuoted("'", "'", "'")) # False - only one quote

        # False value
        self.assertFalse(strlib.isQuoted("[", "[", "]")) # False - only one quote
        self.assertFalse(strlib.isQuoted("{", "{", "}")) # False - only one quote
        self.assertFalse(strlib.isQuoted("(", "(", ")")) # False - only one quote

        self.assertFalse(strlib.isQuoted("'abc'", '"', '"')) # '' is not """
        self.assertFalse(strlib.isQuoted('"abc"', "'", "'")) # """ is not ''
        self.assertFalse(strlib.isQuoted("[abc]", "{", "}")) # [] is not {}
        self.assertFalse(strlib.isQuoted("{abc}", "[", "]")) # {} is not []

        # True "
        self.assertTrue(strlib.isQuoted('""'))
        self.assertTrue(strlib.isQuoted('"abc"'))

        self.assertTrue(strlib.isQuoted('""', '"', '"'))
        self.assertTrue(strlib.isQuoted('"abc"', '"', '"'))

        # True '
        self.assertTrue(strlib.isQuoted("''"))
        self.assertTrue(strlib.isQuoted("'abc'"))

        self.assertTrue(strlib.isQuoted("''", "'", "'"))
        self.assertTrue(strlib.isQuoted("'abc'", "'", "'"))

        # True value
        self.assertTrue(strlib.isQuoted("[]", "[", "]"))
        self.assertTrue(strlib.isQuoted("[abc]", "[", "]"))

        self.assertTrue(strlib.isQuoted("{}", "{", "}"))
        self.assertTrue(strlib.isQuoted("{abc}", "{", "}"))

        self.assertTrue(strlib.isQuoted("()", "(", ")"))
        self.assertTrue(strlib.isQuoted("(abc)", "(", ")"))

    def test_needQuote(self):

        # True
        self.assertTrue(strlib.needQuote(None))
        self.assertTrue(strlib.needQuote(''))
        self.assertTrue(strlib.needQuote(' '))
        self.assertTrue(strlib.needQuote('abc'))

        # True "
        self.assertTrue(strlib.needQuote('"'))           # True - only one quote
        self.assertTrue(strlib.needQuote('"', '"', '"')) # True - only one quote

        # True '
        self.assertTrue(strlib.needQuote("'"))           # True - only one quote
        self.assertTrue(strlib.needQuote("'", "'", "'")) # True - only one quote

        # True value
        self.assertTrue(strlib.needQuote("[", "[", "]")) # True - only one quote
        self.assertTrue(strlib.needQuote("{", "{", "}")) # True - only one quote
        self.assertTrue(strlib.needQuote("(", "(", ")")) # True - only one quote

        self.assertTrue(strlib.needQuote("'abc'", '"', '"')) # '' is not """
        self.assertTrue(strlib.needQuote('"abc"', "'", "'")) # """ is not ''
        self.assertTrue(strlib.needQuote("[abc]", "{", "}")) # [] is not {}
        self.assertTrue(strlib.needQuote("{abc}", "[", "]")) # {} is not []

        # False "
        self.assertFalse(strlib.needQuote('""'))
        self.assertFalse(strlib.needQuote('"abc"'))

        self.assertFalse(strlib.needQuote('""', '"', '"'))
        self.assertFalse(strlib.needQuote('"abc"', '"', '"'))

        # False '
        self.assertFalse(strlib.needQuote("''"))
        self.assertFalse(strlib.needQuote("'abc'"))

        self.assertFalse(strlib.needQuote("''", "'", "'"))
        self.assertFalse(strlib.needQuote("'abc'", "'", "'"))

        # False value
        self.assertFalse(strlib.needQuote("[]", "[", "]"))
        self.assertFalse(strlib.needQuote("[abc]", "[", "]"))

        self.assertFalse(strlib.needQuote("{}", "{", "}"))
        self.assertFalse(strlib.needQuote("{abc}", "{", "}"))

        self.assertFalse(strlib.needQuote("()", "(", ")"))
        self.assertFalse(strlib.needQuote("(abc)", "(", ")"))

    def test_quote(self):

        # quote(None/empty/blank)
        self.assertIsNone(strlib.quote(None))
        self.assertEqual('""', strlib.quote(''))
        self.assertEqual('" "', strlib.quote(' '))

        # quote(value)
        self.assertEqual('"abc"', strlib.quote('abc'))
        self.assertEqual('"abc"', strlib.quote('abc', '"', '"'))
        self.assertEqual("'abc'", strlib.quote('abc', "'", "'"))

        self.assertEqual('" abc "', strlib.quote(' abc '))
        self.assertEqual('" abc "', strlib.quote(' abc ', '"', '"'))
        self.assertEqual("' abc '", strlib.quote(' abc ', "'", "'"))

        self.assertEqual('[abc]', strlib.quote('abc', '[', ']'))
        self.assertEqual('{abc}', strlib.quote('abc', '{', '}'))
        self.assertEqual('(abc)', strlib.quote('abc', '(', ')'))

        # quote "
        self.assertEqual('"""', strlib.quote('"'))
        self.assertEqual('"""', strlib.quote('"', '"', '"'))
        self.assertEqual("\"'\"", strlib.quote("'", '"', '"'))

        # quote '
        self.assertEqual("'''", strlib.quote("'", "'", "'"))
        self.assertEqual('\'"\'', strlib.quote('"', "'", "'"))

    def test_unquote(self):

        # unquote(None/empty/blank)
        self.assertIsNone(strlib.unquote(None))
        self.assertEqual('', strlib.unquote(''))
        self.assertEqual(' ', strlib.unquote(' '))

        # unquote(value) - Nothing
        self.assertEqual('abc', strlib.unquote('abc'))
        self.assertEqual('abc', strlib.unquote('abc', '"', '"'))
        self.assertEqual('abc', strlib.unquote('abc', "'", "'"))

        self.assertEqual(' abc ', strlib.unquote(' abc '))
        self.assertEqual(' abc ', strlib.unquote(' abc ', '"', '"'))
        self.assertEqual(' abc ', strlib.unquote(' abc ', "'", "'"))

        self.assertEqual('abc', strlib.unquote('abc', '[', ']'))
        self.assertEqual('abc', strlib.unquote('abc', '{', '}'))
        self.assertEqual('abc', strlib.unquote('abc', '(', ')'))

        # unquote(value) - Result
        self.assertEqual('abc', strlib.unquote('"abc"'))
        self.assertEqual('abc', strlib.unquote('"abc"', '"', '"'))
        self.assertEqual('abc', strlib.unquote("'abc'", "'", "'"))

        self.assertEqual(' abc ', strlib.unquote('" abc "'))
        self.assertEqual(' abc ', strlib.unquote('" abc "', '"', '"'))
        self.assertEqual(' abc ', strlib.unquote("' abc '", "'", "'"))

        self.assertEqual('abc', strlib.unquote('[abc]', '[', ']'))
        self.assertEqual('abc', strlib.unquote('{abc}', '{', '}'))
        self.assertEqual('abc', strlib.unquote('(abc)', '(', ')'))

    # 4.4

    def test_isColumnSeparator(self):

        # False
        self.assertFalse(strlib.isColumnSeparator(None))
        self.assertFalse(strlib.isColumnSeparator(''))
        self.assertFalse(strlib.isColumnSeparator(' '))
        self.assertFalse(strlib.isColumnSeparator('a'))
        self.assertFalse(strlib.isColumnSeparator('abc'))  # not char - string

        # True
        self.assertTrue(strlib.isColumnSeparator('\r'))
        self.assertTrue(strlib.isColumnSeparator('\n'))
        self.assertTrue(strlib.isColumnSeparator('\t'))

    def test_isColumnText(self):

        # False
        self.assertFalse(strlib.isColumnText(None))
        self.assertFalse(strlib.isColumnText(''))
        self.assertFalse(strlib.isColumnText(' '))
        self.assertFalse(strlib.isColumnText('a'))
        self.assertFalse(strlib.isColumnText('abc'))
        self.assertFalse(strlib.isColumnText('abc def'))

        # True
        self.assertTrue(strlib.isColumnText('\r'))
        self.assertTrue(strlib.isColumnText('\n'))
        self.assertTrue(strlib.isColumnText('\t'))
        self.assertTrue(strlib.isColumnText('abc\rdef'))
        self.assertTrue(strlib.isColumnText('abc\ndef'))
        self.assertTrue(strlib.isColumnText('abc\r\ndef'))
        self.assertTrue(strlib.isColumnText('abc\r\ndef\txyz'))

    def test_isLineText(self):

        # True
        self.assertTrue(strlib.isLineText(None))
        self.assertTrue(strlib.isLineText(''))
        self.assertTrue(strlib.isLineText(' '))
        self.assertTrue(strlib.isLineText('a'))
        self.assertTrue(strlib.isLineText('abc'))
        self.assertTrue(strlib.isLineText('abc def'))

        # False
        self.assertFalse(strlib.isLineText('\r'))
        self.assertFalse(strlib.isLineText('\n'))
        self.assertFalse(strlib.isLineText('\t'))
        self.assertFalse(strlib.isLineText('abc\rdef'))
        self.assertFalse(strlib.isLineText('abc\ndef'))
        self.assertFalse(strlib.isLineText('abc\r\ndef'))
        self.assertFalse(strlib.isLineText('abc\r\ndef\txyz'))

    # 5.1

    def test_countChars(self):

        # countChars(None, value)
        self.assertEqual(0, strlib.countChars(None, None))
        self.assertEqual(0, strlib.countChars(None, ''))
        self.assertEqual(0, strlib.countChars(None, ' '))
        self.assertEqual(0, strlib.countChars(None, 'a'))
        self.assertEqual(0, strlib.countChars(None, 'abc')) # not char - string

        # countChars(empty, value)
        self.assertEqual(0, strlib.countChars('', None))
        self.assertEqual(0, strlib.countChars('', ''))
        self.assertEqual(0, strlib.countChars('', ' '))
        self.assertEqual(0, strlib.countChars('', 'a'))
        self.assertEqual(0, strlib.countChars('', 'abc'))   # not char - string

        # countChars(blank, value)
        self.assertEqual(0, strlib.countChars(' ', None))
        self.assertEqual(0, strlib.countChars(' ', ''))
        self.assertEqual(1, strlib.countChars(' ', ' '))    # OK
        self.assertEqual(0, strlib.countChars(' ', 'a'))
        self.assertEqual(0, strlib.countChars(' ', 'abc'))  # not char - string

        # countChars(char, value)
        self.assertEqual(0, strlib.countChars('a', None))
        self.assertEqual(0, strlib.countChars('a', ''))
        self.assertEqual(0, strlib.countChars('a', ' '))
        self.assertEqual(1, strlib.countChars('a', 'a'))    # OK
        self.assertEqual(0, strlib.countChars('a', 'abc'))  # not char - string

        # countChars(value, value)
        self.assertEqual(0, strlib.countChars('abcabcc', None))
        self.assertEqual(0, strlib.countChars('abcabcc', ''))
        self.assertEqual(0, strlib.countChars('abcabcc', ' '))
        self.assertEqual(2, strlib.countChars('abcabcc', 'a'))    # OK - 2
        self.assertEqual(2, strlib.countChars('abcabcc', 'b'))    # OK - 2
        self.assertEqual(3, strlib.countChars('abcabcc', 'c'))    # OK - 3
        self.assertEqual(0, strlib.countChars('abcabcc', 'abc'))  # not char - string

        self.assertEqual(2, strlib.countChars('Hi, my name is Alex, tell me something about you', ','))  # OK - 2

    def test_countStrings(self):

        # countStrings(None, value)
        self.assertEqual(0, strlib.countStrings(None, None))
        self.assertEqual(0, strlib.countStrings(None, ''))
        self.assertEqual(0, strlib.countStrings(None, ' '))
        self.assertEqual(0, strlib.countStrings(None, 'a'))
        self.assertEqual(0, strlib.countStrings(None, 'abc'))

        # countStrings(empty, value)
        self.assertEqual(0, strlib.countStrings('', None))
        self.assertEqual(0, strlib.countStrings('', ''))
        self.assertEqual(0, strlib.countStrings('', ' '))
        self.assertEqual(0, strlib.countStrings('', 'a'))
        self.assertEqual(0, strlib.countStrings('', 'abc'))

        # countStrings(blank, value)
        self.assertEqual(0, strlib.countStrings(' ', None))
        self.assertEqual(0, strlib.countStrings(' ', ''))
        self.assertEqual(1, strlib.countStrings(' ', ' '))       # Ok - 1
        self.assertEqual(0, strlib.countStrings(' ', 'a'))
        self.assertEqual(0, strlib.countStrings(' ', 'abc'))

        # countStrings(char, value)
        self.assertEqual(0, strlib.countStrings('a', None))
        self.assertEqual(0, strlib.countStrings('a', ''))
        self.assertEqual(0, strlib.countStrings('a', ' '))
        self.assertEqual(1, strlib.countStrings('a', 'a'))       # Ok - 1
        self.assertEqual(0, strlib.countStrings('a', 'abc'))

        # countStrings(value, value)
        self.assertEqual(0, strlib.countStrings('abcxyzabc', None))
        self.assertEqual(0, strlib.countStrings('abcxyzabc', ''))
        self.assertEqual(0, strlib.countStrings('abcxyzabc', ' '))
        self.assertEqual(2, strlib.countStrings('abcxyzabc', 'a'))   # Ok - 2
        self.assertEqual(2, strlib.countStrings('abcxyzabc', 'abc')) # Ok - 2
        self.assertEqual(1, strlib.countStrings('abcxyzabc', 'xyz')) # Ok - 1

        self.assertEqual(1, strlib.countStrings('Hello world! It is good world!', 'Hello')) # Ok - 1
        self.assertEqual(2, strlib.countStrings('Hello world! It is good world!', 'world')) # Ok - 2
        self.assertEqual(2, strlib.countStrings('Hello world! It is good world!', '!'))     # Ok - 2

    def test_countWords(self):

        # separators=default

        # countWords(None)
        self.assertEqual(0, strlib.countWords(None))

        # countWords(empty)
        self.assertEqual(0, strlib.countWords(''))

        # countWords(blank)
        self.assertEqual(0, strlib.countWords(' '))  # ' ' separator not include

        # countWords(char)
        self.assertEqual(1, strlib.countWords('a'))

        # countWords(value)
        self.assertEqual(1, strlib.countWords('Hello'))

        # separators=value

        # countWords(None, value)
        self.assertEqual(0, strlib.countWords(None, None))
        self.assertEqual(0, strlib.countWords(None, ''))
        self.assertEqual(0, strlib.countWords(None, ' '))
        self.assertEqual(0, strlib.countWords(None, ','))
        self.assertEqual(0, strlib.countWords(None, ',; '))

        # countWords(empty, value)
        self.assertEqual(0, strlib.countWords('', None))
        self.assertEqual(0, strlib.countWords('', ''))
        self.assertEqual(0, strlib.countWords('', ' '))
        self.assertEqual(0, strlib.countWords('', ','))
        self.assertEqual(0, strlib.countWords('', ',; '))

        # countWords(blank, value)
        self.assertEqual(0, strlib.countWords(' ', None))
        self.assertEqual(1, strlib.countWords(' ', ''))        # Ok - 1 (???)
        self.assertEqual(0, strlib.countWords(' ', ' '))       # ' ' separator not include
        self.assertEqual(1, strlib.countWords(' ', ','))       # Ok - 1
        self.assertEqual(0, strlib.countWords(' ', ',; '))     # ' ' separator not include

        # countWords(char, value)
        self.assertEqual(1, strlib.countWords('a', None))
        self.assertEqual(1, strlib.countWords('a', ''))
        self.assertEqual(1, strlib.countWords('a', ' '))
        self.assertEqual(1, strlib.countWords('a', ','))
        self.assertEqual(1, strlib.countWords('a', ',; '))
        self.assertEqual(0, strlib.countWords('a', 'a'))       # Ok - 1
        self.assertEqual(0, strlib.countWords('a', 'abc'))     # 'a' separator not include

        # countWords(value, value)
        self.assertEqual(1, strlib.countWords('Hello', None))
        self.assertEqual(1, strlib.countWords('Hello', ''))
        self.assertEqual(1, strlib.countWords('Hello', ' '))
        self.assertEqual(1, strlib.countWords('Hello', ','))
        self.assertEqual(1, strlib.countWords('Hello', ',; '))

        self.assertEqual(2, strlib.countWords('Hello', 'e'))   # Ok - 2
        self.assertEqual(2, strlib.countWords('Hello', 'eo'))  # Ok - 2
        self.assertEqual(2, strlib.countWords('Hello', 'l'))   # Ok - 1

        # separate=False
        self.assertEqual(1, strlib.countWords('Hello-world'))        # ['Hello-world']:         '-' is not separator

        # separate=True
        self.assertEqual(2, strlib.countWords('Hello world!'))
        self.assertEqual(3, strlib.countWords('Hello - world!'))     # ['Hello', '-', 'world']: '-' is not separator
        self.assertEqual(3, strlib.countWords('Hello  -  world!'))   # ['Hello', '-', 'world']: '-' is not separator

        self.assertEqual(2, strlib.countWords('Hello- world!'))      # ['Hello-', 'world']:     '-' is not separator
        self.assertEqual(2, strlib.countWords('Hello -world!'))      # ['Hello', '-world']:     '-' is not separator

        ##

        self.assertEqual(6, strlib.countWords('Hello world! It is good world!'))          # ['Hello', 'world', 'It', 'is', 'good', 'world']
        self.assertEqual(6, strlib.countWords('Hello world! It is good world!', ' !?.'))  # ['Hello', 'world', 'It', 'is', 'good', 'world']
        self.assertEqual(2, strlib.countWords('Hello world! It is good world!', '!'))     # ['Hello world', ' It is good world']]

    def test_countLines(self):

        # countLines(None)
        self.assertEqual(0, strlib.countLines(None))

        # countLines(empty)
        self.assertEqual(0, strlib.countLines(''))

        # countLines(blank)
        self.assertEqual(1, strlib.countLines(' '))

        # countLines(char)
        self.assertEqual(1, strlib.countLines('a'))

        # countLines(value)
        self.assertEqual(1, strlib.countLines('Hello'))

        # split=False
        self.assertEqual(1, strlib.countLines('Hello world! It is good world!'))
        self.assertEqual(1, strlib.countLines('Hello world!\tIt is good world!'))

        # split=True
        self.assertEqual(2, strlib.countLines('Hello world!\rIt is good world!'))     # ['Hello world!', 'It is good world!']: \r    - OK
        self.assertEqual(2, strlib.countLines('Hello world!\nIt is good world!'))     # ['Hello world!', 'It is good world!']: \n    - OK
        self.assertEqual(2, strlib.countLines('Hello world!\r\nIt is good world!'))   # ['Hello world!', 'It is good world!']: \r\n  - OK 

        self.assertEqual(2, strlib.countLines('Hello world!\fIt is good world!'))     # ['Hello world!', 'It is good world!']: \f    - OK (???)
        self.assertEqual(2, strlib.countLines('Hello world!\vIt is good world!'))     # ['Hello world!', 'It is good world!']: \v    - OK (???)

        # IMPORTANT !!!
        self.assertEqual(3, strlib.countLines('Hello world!\n\rIt is good world!'))   # ['Hello world!', '', 'It is good world!']: \n\r  - OK
        self.assertEqual(3, strlib.countLines('Hello world!\n\nIt is good world!'))   # ['Hello world!', '', 'It is good world!']: \n\n  - OK
        self.assertEqual(3, strlib.countLines('Hello world!\r\rIt is good world!'))   # ['Hello world!', '', 'It is good world!']: \r\r  - OK

        self.assertEqual(3, strlib.countLines('Hello world!\n\r\nIt is good world!')) # ['Hello world!', '', 'It is good world!']: \n\r\n - OK
        self.assertEqual(4, strlib.countLines('Hello world!\n\n\rIt is good world!')) # ['Hello world!', '', '', 'It is good world!']: \n\n\r - OK

    # 6.1

    def test_replaceAll_str(self):

        # replaceAll(None, value, value): None -> None
        self.assertIsNone(strlib.replaceAll(None, None, None))
        self.assertIsNone(strlib.replaceAll(None, None, ''))
        self.assertIsNone(strlib.replaceAll(None, None, ' '))
        self.assertIsNone(strlib.replaceAll(None, None, 'abc'))

        self.assertIsNone(strlib.replaceAll(None, '', None))
        self.assertIsNone(strlib.replaceAll(None, '', ''))
        self.assertIsNone(strlib.replaceAll(None, '', ' '))
        self.assertIsNone(strlib.replaceAll(None, '', 'abc'))

        self.assertIsNone(strlib.replaceAll(None, ' ', None))
        self.assertIsNone(strlib.replaceAll(None, ' ', ''))
        self.assertIsNone(strlib.replaceAll(None, ' ', ' '))
        self.assertIsNone(strlib.replaceAll(None, ' ', 'abc'))

        self.assertIsNone(strlib.replaceAll(None, 'abc', None))
        self.assertIsNone(strlib.replaceAll(None, 'abc', ''))
        self.assertIsNone(strlib.replaceAll(None, 'abc', ' '))
        self.assertIsNone(strlib.replaceAll(None, 'abc', 'abc'))
        self.assertIsNone(strlib.replaceAll(None, 'abc', 'def'))

        # replaceAll(empty, value, value): '' -> ''
        self.assertEqual('', strlib.replaceAll('', None, None))
        self.assertEqual('', strlib.replaceAll('', None, ''))
        self.assertEqual('', strlib.replaceAll('', None, ' '))
        self.assertEqual('', strlib.replaceAll('', None, 'abc'))

        self.assertEqual('', strlib.replaceAll('', '', None))
        self.assertEqual('', strlib.replaceAll('', '', ''))
        self.assertEqual('', strlib.replaceAll('', '', ' '))
        self.assertEqual('', strlib.replaceAll('', '', 'abc'))

        self.assertEqual('', strlib.replaceAll('', ' ', None))
        self.assertEqual('', strlib.replaceAll('', ' ', ''))
        self.assertEqual('', strlib.replaceAll('', ' ', ' '))
        self.assertEqual('', strlib.replaceAll('', ' ', 'abc'))

        self.assertEqual('', strlib.replaceAll('', 'abc', None))
        self.assertEqual('', strlib.replaceAll('', 'abc', ''))
        self.assertEqual('', strlib.replaceAll('', 'abc', ' '))
        self.assertEqual('', strlib.replaceAll('', 'abc', 'abc'))
        self.assertEqual('', strlib.replaceAll('', 'abc', 'def'))

        # replaceAll(value, value, value)
        self.assertEqual('abc', strlib.replaceAll('abc', None, None))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ''))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ' '))
        self.assertEqual('abc', strlib.replaceAll('abc', None, 'abc'))

        self.assertEqual('abc', strlib.replaceAll('abc', '', None))
        self.assertEqual('abc', strlib.replaceAll('abc', '', ''))
        self.assertEqual('abc', strlib.replaceAll('abc', '', ' '))
        self.assertEqual('abc', strlib.replaceAll('abc', '', 'abc'))

        self.assertEqual('abc', strlib.replaceAll('abc', ' ', None))
        self.assertEqual('abc', strlib.replaceAll('abc', ' ', ''))
        self.assertEqual('abc', strlib.replaceAll('abc', ' ', ' '))
        self.assertEqual('abc', strlib.replaceAll('abc', ' ', 'abc'))

        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', None))
        self.assertEqual('', strlib.replaceAll('abc', 'abc', ''))              # remove
        self.assertEqual(' ', strlib.replaceAll('abc', 'abc', ' '))            # blank
        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', 'abc'))        # notning
        self.assertEqual('def', strlib.replaceAll('abc', 'abc', 'def'))        # replace

        # False
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', 'd', ''))
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', 'd', '1'))
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', 'def', '123'))

        # True
        self.assertEqual('bc xyz bc', strlib.replaceAll('abc xyz abc', 'a', ''))         # remove
        self.assertEqual('1bc xyz 1bc', strlib.replaceAll('abc xyz abc', 'a', '1'))      # replace
        self.assertEqual('123 xyz 123', strlib.replaceAll('abc xyz abc', 'abc', '123'))  # replace


    def test_replaceAll_list(self):

        # replaceAll(None, value, value): None -> None
        self.assertIsNone(strlib.replaceAll(None, None, []))
        self.assertIsNone(strlib.replaceAll(None, None, [None]))
        self.assertIsNone(strlib.replaceAll(None, None, ['']))
        self.assertIsNone(strlib.replaceAll(None, None, [' ']))
        self.assertIsNone(strlib.replaceAll(None, None, ['abc']))
        self.assertIsNone(strlib.replaceAll(None, None, ['abc', 'def']))

        self.assertIsNone(strlib.replaceAll(None, [], None))
        self.assertIsNone(strlib.replaceAll(None, [], []))
        self.assertIsNone(strlib.replaceAll(None, [], [None]))
        self.assertIsNone(strlib.replaceAll(None, [], ['']))
        self.assertIsNone(strlib.replaceAll(None, [], [' ']))
        self.assertIsNone(strlib.replaceAll(None, [], ['abc']))
        self.assertIsNone(strlib.replaceAll(None, [], ['abc', 'def']))

        self.assertIsNone(strlib.replaceAll(None, [None], None))
        self.assertIsNone(strlib.replaceAll(None, [None], []))
        self.assertIsNone(strlib.replaceAll(None, [None], [None]))
        self.assertIsNone(strlib.replaceAll(None, [None], ['']))
        self.assertIsNone(strlib.replaceAll(None, [None], [' ']))
        self.assertIsNone(strlib.replaceAll(None, [None], ['abc']))
        self.assertIsNone(strlib.replaceAll(None, [None], ['abc', 'def']))

        self.assertIsNone(strlib.replaceAll(None, ['abc'], None))
        self.assertIsNone(strlib.replaceAll(None, ['abc'], []))
        self.assertIsNone(strlib.replaceAll(None, ['abc'], [None]))
        self.assertIsNone(strlib.replaceAll(None, ['abc'], ['']))
        self.assertIsNone(strlib.replaceAll(None, ['abc'], [' ']))
        self.assertIsNone(strlib.replaceAll(None, ['abc'], ['abc']))
        self.assertIsNone(strlib.replaceAll(None, ['abc'], ['abc', 'def']))

        # replaceAll(empty, value, value): empty -> empty
        self.assertEqual('', strlib.replaceAll('', None, []))
        self.assertEqual('', strlib.replaceAll('', None, [None]))
        self.assertEqual('', strlib.replaceAll('', None, ['']))
        self.assertEqual('', strlib.replaceAll('', None, [' ']))
        self.assertEqual('', strlib.replaceAll('', None, ['abc']))
        self.assertEqual('', strlib.replaceAll('', None, ['abc', 'def']))

        self.assertEqual('', strlib.replaceAll('', [], None))
        self.assertEqual('', strlib.replaceAll('', [], []))
        self.assertEqual('', strlib.replaceAll('', [], [None]))
        self.assertEqual('', strlib.replaceAll('', [], ['']))
        self.assertEqual('', strlib.replaceAll('', [], [' ']))
        self.assertEqual('', strlib.replaceAll('', [], ['abc']))
        self.assertEqual('', strlib.replaceAll('', [], ['abc', 'def']))

        self.assertEqual('', strlib.replaceAll('', ['abc'], None))
        self.assertEqual('', strlib.replaceAll('', ['abc'], []))
        self.assertEqual('', strlib.replaceAll('', ['abc'], [None]))
        self.assertEqual('', strlib.replaceAll('', ['abc'], ['']))
        self.assertEqual('', strlib.replaceAll('', ['abc'], [' ']))
        self.assertEqual('', strlib.replaceAll('', ['abc'], ['abc']))
        self.assertEqual('', strlib.replaceAll('', ['abc'], ['abc', 'def']))

        # replaceAll(value, value, value): empty -> empty
        self.assertEqual('abc', strlib.replaceAll('abc', None, []))
        self.assertEqual('abc', strlib.replaceAll('abc', None, [None]))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ['']))
        self.assertEqual('abc', strlib.replaceAll('abc', None, [' ']))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ['abc']))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ['abc', 'def']))

        self.assertEqual('abc', strlib.replaceAll('abc', [], None))
        self.assertEqual('abc', strlib.replaceAll('abc', [], []))
        self.assertEqual('abc', strlib.replaceAll('abc', [], [None]))
        self.assertEqual('abc', strlib.replaceAll('abc', [], ['']))
        self.assertEqual('abc', strlib.replaceAll('abc', [], [' ']))
        self.assertEqual('abc', strlib.replaceAll('abc', [], ['abc']))
        self.assertEqual('abc', strlib.replaceAll('abc', [], ['abc', 'def']))

        self.assertEqual('abc', strlib.replaceAll('abc', ['abc'], None))
        self.assertEqual('abc', strlib.replaceAll('abc', ['abc'], []))
        self.assertEqual('abc', strlib.replaceAll('abc', ['abc'], [None]))
        self.assertEqual('', strlib.replaceAll('abc', ['abc'], ['']))              # remove
        self.assertEqual(' ', strlib.replaceAll('abc', ['abc'], [' ']))            # blank

        self.assertEqual('abc', strlib.replaceAll('abc', ['abc'], ['abc']))        # nothing
        self.assertEqual('abc', strlib.replaceAll('abc', ['abc'], ['abc', 'def'])) # nothing

        ##### incorrect parameters ################################

        self.assertIsNone(strlib.replaceAll(None, '', []))
        self.assertIsNone(strlib.replaceAll(None, '', [None]))
        self.assertIsNone(strlib.replaceAll(None, '', ['abc']))
        self.assertIsNone(strlib.replaceAll(None, '', ['abc', 'def']))

        self.assertIsNone(strlib.replaceAll(None, ' ', []))
        self.assertIsNone(strlib.replaceAll(None, ' ', [None]))
        self.assertIsNone(strlib.replaceAll(None, ' ', ['abc']))
        self.assertIsNone(strlib.replaceAll(None, ' ', ['abc', 'def']))

        self.assertIsNone(strlib.replaceAll(None, 'abc', []))
        self.assertIsNone(strlib.replaceAll(None, 'abc', [None]))
        self.assertIsNone(strlib.replaceAll(None, 'abc', ['abc']))
        self.assertIsNone(strlib.replaceAll(None, 'abc', ['abc', 'def']))
        self.assertIsNone(strlib.replaceAll(None, 'abc', ['def', 'abc']))

        # replaceAll(empty, value, value): '' -> ''
        self.assertEqual('', strlib.replaceAll('', None, []))
        self.assertEqual('', strlib.replaceAll('', None, [None]))
        self.assertEqual('', strlib.replaceAll('', None, ['abc']))
        self.assertEqual('', strlib.replaceAll('', None, ['abc', 'def']))

        self.assertEqual('', strlib.replaceAll('', '', []))
        self.assertEqual('', strlib.replaceAll('', '', [None]))
        self.assertEqual('', strlib.replaceAll('', '', ['abc']))
        self.assertEqual('', strlib.replaceAll('', '', ['abc', 'def']))

        self.assertEqual('', strlib.replaceAll('', ' ', []))
        self.assertEqual('', strlib.replaceAll('', ' ', [None]))
        self.assertEqual('', strlib.replaceAll('', ' ', ['abc']))
        self.assertEqual('', strlib.replaceAll('', ' ', ['abc', 'def']))

        self.assertEqual('', strlib.replaceAll('', 'abc', []))
        self.assertEqual('', strlib.replaceAll('', 'abc', [None]))
        self.assertEqual('', strlib.replaceAll('', 'abc', ['abc']))
        self.assertEqual('', strlib.replaceAll('', 'abc', ['abc', 'def']))
        self.assertEqual('', strlib.replaceAll('', 'abc', ['def', 'abc']))

        # replaceAll(value, value, value)
        self.assertEqual('abc', strlib.replaceAll('abc', None, []))
        self.assertEqual('abc', strlib.replaceAll('abc', None, [None]))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ['abc']))
        self.assertEqual('abc', strlib.replaceAll('abc', None, ['abc', 'def']))

        self.assertEqual('abc', strlib.replaceAll('abc', '', []))
        self.assertEqual('abc', strlib.replaceAll('abc', '', [None]))
        self.assertEqual('abc', strlib.replaceAll('abc', '', ['abc']))
        self.assertEqual('abc', strlib.replaceAll('abc', '', ['abc', 'def']))

        self.assertEqual('abc', strlib.replaceAll('abc', ' ', []))
        self.assertEqual('abc', strlib.replaceAll('abc', ' ', [None]))
        self.assertEqual('abc', strlib.replaceAll('abc', ' ', ['abc']))
        self.assertEqual('abc', strlib.replaceAll('abc', ' ', ['abc', 'def']))

        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', []))
        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', [None]))
        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', ['abc']))
        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', ['abc', 'def']))
        self.assertEqual('abc', strlib.replaceAll('abc', 'abc', ['def', 'abc']))

        # False
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', [], []))
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', [], ['def']))                               # size <>: 0,1
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', ['def'], []))                               # size <>: 1,0
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', ['def'], ['123']))

        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', ['def', 'qwe'], ['123', '456']))

        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', ['def'], ['123', '456']))                   # size <>: 1,2
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', ['def', 'qwe'], ['123']))                   # size <>: 2,1

        # True
        self.assertEqual('123 xyz 123', strlib.replaceAll('abc xyz abc', ['abc'], ['123']))

        self.assertEqual('123 456 123', strlib.replaceAll('abc xyz abc', ['abc', 'xyz'], ['123', '456']))
        self.assertEqual('123 456 123 def', strlib.replaceAll('abc xyz abc def', ['abc', 'xyz'], ['123', '456']))

        self.assertEqual('123 xyz 123', strlib.replaceAll('abc xyz abc', ['abc'], ['123', '456']))                   # size <>: 1,2
        self.assertEqual('123 xyz 123 def', strlib.replaceAll('abc xyz abc def', ['abc', 'xyz'], ['123']))           # size <>: 2,1

    def test_replaceAll_map(self):

        # replaceAll(None, value): None -> None
        self.assertIsNone(strlib.replaceAll(None, None))
        self.assertIsNone(strlib.replaceAll(None, {}))
        self.assertIsNone(strlib.replaceAll(None, {'': 'abc'}))
        self.assertIsNone(strlib.replaceAll(None, {'abc': ''}))
        self.assertIsNone(strlib.replaceAll(None, {'abc': 'def'}))

        # replaceAll(empty, value): '' -> ''
        self.assertEqual('', strlib.replaceAll('', None))
        self.assertEqual('', strlib.replaceAll('', {}))
        self.assertEqual('', strlib.replaceAll('', {'': 'abc'}))
        self.assertEqual('', strlib.replaceAll('', {'abc': ''}))
        self.assertEqual('', strlib.replaceAll('', {'abc': 'def'}))

        # replaceAll(value, value):
        self.assertEqual('abc', strlib.replaceAll('abc', None))
        self.assertEqual('abc', strlib.replaceAll('abc', {}))
        self.assertEqual('abc', strlib.replaceAll('abc', {'': 'abc'}))
        self.assertEqual('', strlib.replaceAll('abc', {'abc': ''}))       # remove
        self.assertEqual('def', strlib.replaceAll('abc', {'abc': 'def'})) # replace

        # False
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {}))
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {None: 'def'}))                             # size <>: 0,1        
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'': 'def'}))                               # size <>: 0,1
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': None}))                             # size <>: 1,0
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': ''}))                               # size <>: 1,0
        
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': '123'}))
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': '123', 'qwe': '456'}))

        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': '123', None: '456'}))                # size <>: 1,2
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': '123', '': '456'}))                  # size <>: 1,2

        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': '123', 'qwe': None}))                # size <>: 2,1
        self.assertEqual('abc xyz abc', strlib.replaceAll('abc xyz abc', {'def': '123', 'qwe': ''}))                  # size <>: 2,1

        # True
        self.assertEqual('123 xyz 123', strlib.replaceAll('abc xyz abc', {'abc': '123'}))

        self.assertEqual('123 456 123', strlib.replaceAll('abc xyz abc', {'abc': '123', 'xyz': '456'}))
        self.assertEqual('123 456 123 def', strlib.replaceAll('abc xyz abc def', {'abc': '123', 'xyz': '456'}))

        self.assertEqual('123 xyz 123', strlib.replaceAll('abc xyz abc', {'abc': '123', None: '456'}))                # size <>: 1,2
        self.assertEqual('123 xyz 123 def', strlib.replaceAll('abc xyz abc def', {'abc': '123', 'xyz': None}))        # size <>: 2,1

    # 7.1

    # alias=splitBySeparator
    def test_split(self):

        # split(None)
        self.assertEqual([], strlib.split(None))
        self.assertEqual([], strlib.split(None, None))
        self.assertEqual([], strlib.split(None, ''))
        self.assertEqual([], strlib.split(None, ' '))
        self.assertEqual([], strlib.split(None, ','))

        # split(empty)
        self.assertEqual([], strlib.split(''))
        self.assertEqual([], strlib.split('', None))
        self.assertEqual([], strlib.split('', ''))
        self.assertEqual([], strlib.split('', ' '))
        self.assertEqual([], strlib.split('', ','))

        # split(blank)
        self.assertEqual([], strlib.split(' '))             # split by default. ' ' - separator by default, not include
        self.assertEqual([], strlib.split(' ', None))       # split by default. ' ' - separator by default, not include
        self.assertEqual([' '], strlib.split(' ', ''))
        self.assertEqual(['', ''], strlib.split(' ', ' '))  # preserveAll = True by default (???)
        self.assertEqual([' '], strlib.split(' ', ','))

        # False
        self.assertEqual(['abc,def,xyz'], strlib.split('abc,def,xyz', '|'))
        self.assertEqual(['abc|def|xyz'], strlib.split('abc|def|xyz', ','))
        
        # True
        # separator=default
        self.assertEqual(['abc', 'def', 'xyz'], strlib.split('abc\ndef\rxyz'))
        self.assertEqual(['abc', 'def', 'xyz'], strlib.split('abc\f def\v \txyz'))

        # separator=value
        self.assertEqual(['abc', 'def', 'xyz'], strlib.split('abc,def,xyz', ','))                             # preserveAll = True by default
        self.assertEqual(['abc', ' def', ' xyz'], strlib.split('abc, def, xyz', ','))                         # preserveAll = True by default
        self.assertEqual(['abc', 'def', 'xyz'], strlib.split('abc, def, xyz', ', '))                          # preserveAll = True by default

        self.assertEqual(['', 'abc', 'def', '', 'xyz', ''], strlib.split(',abc,def,,xyz,', ',', True))        # preserveAll = True
        self.assertEqual(['', 'abc', ' def', '', ' xyz', ''], strlib.split(',abc, def,, xyz,', ',', True))    # preserveAll = True
        self.assertEqual(['', 'abc', 'def', '', 'xyz', ''], strlib.split(', abc, def, , xyz, ', ', ', True))  # preserveAll = True

        self.assertEqual(['abc', 'def', 'xyz'], strlib.split(',abc,def,,xyz,', ',', False))                   # preserveAll = False
        self.assertEqual(['abc', ' def', ' xyz'], strlib.split(',abc, def,, xyz,', ',', False))               # preserveAll = False
        self.assertEqual(['abc', 'def', 'xyz'], strlib.split(', abc, def, , xyz, ', ', ', False))             # preserveAll = False

    def test_splitBySeparator(self):

        # splitBySeparator(None, value)
        self.assertEqual([], strlib.splitBySeparator(None, None))
        self.assertEqual([], strlib.splitBySeparator(None, ''))
        self.assertEqual([], strlib.splitBySeparator(None, ' '))
        self.assertEqual([], strlib.splitBySeparator(None, ','))

        # splitBySeparator(empty, value)
        self.assertEqual([], strlib.splitBySeparator('', None))
        self.assertEqual([], strlib.splitBySeparator('', ''))
        self.assertEqual([], strlib.splitBySeparator('', ' '))
        self.assertEqual([], strlib.splitBySeparator('', ','))

        # splitBySeparator(blank, value)
        self.assertEqual([], strlib.splitBySeparator(' ', None))       # split by default. ' ' - separator by default, not include
        self.assertEqual([' '], strlib.splitBySeparator(' ', ''))
        self.assertEqual(['', ''], strlib.splitBySeparator(' ', ' '))  # preserveAll = True by default (???)
        self.assertEqual([' '], strlib.splitBySeparator(' ', ','))

        # False
        self.assertEqual(['abc,def,xyz'], strlib.splitBySeparator('abc,def,xyz', '|'))
        self.assertEqual(['abc|def|xyz'], strlib.splitBySeparator('abc|def|xyz', ','))

        # True
        # separator=value
        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparator('abc,def,xyz', ','))                             # preserveAll = True by default
        self.assertEqual(['abc', ' def', ' xyz'], strlib.splitBySeparator('abc, def, xyz', ','))                         # preserveAll = True by default
        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparator('abc, def, xyz', ', '))                          # preserveAll = True by default

        self.assertEqual(['', 'abc', 'def', '', 'xyz', ''], strlib.splitBySeparator(',abc,def,,xyz,', ',', True))        # preserveAll = True
        self.assertEqual(['', 'abc', ' def', '', ' xyz', ''], strlib.splitBySeparator(',abc, def,, xyz,', ',', True))    # preserveAll = True
        self.assertEqual(['', 'abc', 'def', '', 'xyz', ''], strlib.splitBySeparator(', abc, def, , xyz, ', ', ', True))  # preserveAll = True

        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparator(',abc,def,,xyz,', ',', False))                   # preserveAll = False
        self.assertEqual(['abc', ' def', ' xyz'], strlib.splitBySeparator(',abc, def,, xyz,', ',', False))               # preserveAll = False
        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparator(', abc, def, , xyz, ', ', ', False))             # preserveAll = False

    def test_splitBySeparators(self):

        # splitBySeparators(None, value)
        self.assertEqual([], strlib.splitBySeparators(None, None))
        self.assertEqual([], strlib.splitBySeparators(None, ''))
        self.assertEqual([], strlib.splitBySeparators(None, ' '))
        self.assertEqual([], strlib.splitBySeparators(None, ','))

        # splitBySeparators(empty, value)
        self.assertEqual([], strlib.splitBySeparators('', None))
        self.assertEqual([], strlib.splitBySeparators('', ''))
        self.assertEqual([], strlib.splitBySeparators('', ' '))
        self.assertEqual([], strlib.splitBySeparators('', ','))

        # splitBySeparators(blank, value)
        self.assertEqual([], strlib.splitBySeparators(' ', None))       # split by default. ' ' - separator by default, not include
        self.assertEqual([' '], strlib.splitBySeparators(' ', ''))
        self.assertEqual(['', ''], strlib.splitBySeparators(' ', ' '))  # preserveAll = True by default (???)
        self.assertEqual([' '], strlib.splitBySeparators(' ', ','))

        # False
        # one separator
        self.assertEqual(['abc,def,xyz'], strlib.splitBySeparators('abc,def,xyz', '|'))
        self.assertEqual(['abc|def|xyz'], strlib.splitBySeparators('abc|def|xyz', ','))

        # many separators
        self.assertEqual(['abc,def,xyz'], strlib.splitBySeparators('abc,def,xyz', '|;.-'))
        self.assertEqual(['abc|def|xyz'], strlib.splitBySeparators('abc|def|xyz', ',;.-'))

        # True
        # one separator
        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparators('abc,def,xyz', ','))
        self.assertEqual(['abc', ' def', ' xyz'], strlib.splitBySeparators('abc, def, xyz', ','))

        self.assertEqual(['abc', 'def', '', 'xyz'], strlib.splitBySeparators('abc,def,,xyz', ',', True))
        self.assertEqual(['abc', ' def', '', ' xyz'], strlib.splitBySeparators('abc, def,, xyz', ',', True))

        # many separators
        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparators('abc,def;xyz', ',;'))
        self.assertEqual(['abc', ' def', ' xyz'], strlib.splitBySeparators('abc, def; xyz', ',;'))

        self.assertEqual(['abc', 'def', '', 'xyz'], strlib.splitBySeparators('abc,def;,xyz', ',;', True))
        self.assertEqual(['abc', ' def', '', ' xyz'], strlib.splitBySeparators('abc, def;, xyz', ',;', True))

        # many separators - skip
        self.assertEqual(['abc', 'def', 'xyz'], strlib.splitBySeparators('abc,def;xyz', ',;.-'))
        self.assertEqual(['abc', ' def', ' xyz'], strlib.splitBySeparators('abc, def; xyz', ',;.-'))

        self.assertEqual(['abc', 'def', '', 'xyz'], strlib.splitBySeparators('abc,def;,xyz', ',;.-', True))
        self.assertEqual(['abc', ' def', '', ' xyz'], strlib.splitBySeparators('abc, def;, xyz', ',;.-', True))

    # - splitTrim
    # - splitTrimBySeparator
    # - splitTrimBySeparators

    def test_splitWords(self):

        # separators=default

        # splitWords(None)
        self.assertEqual([], strlib.splitWords(None))

        # splitWords(empty)
        self.assertEqual([], strlib.splitWords(''))

        # splitWords(blank)
        self.assertEqual([], strlib.splitWords(' '))

        # splitWords(value)
        self.assertEqual(['Hello'], strlib.splitWords('Hello'))


        # separators=value

        # splitWords(None, value)
        self.assertEqual([], strlib.splitWords(None, None))
        self.assertEqual([], strlib.splitWords(None, ''))
        self.assertEqual([], strlib.splitWords(None, ' '))
        self.assertEqual([], strlib.splitWords(None, ','))

        # splitWords(empty, value)
        self.assertEqual([], strlib.splitWords('', None))
        self.assertEqual([], strlib.splitWords('', ''))
        self.assertEqual([], strlib.splitWords('', ' '))
        self.assertEqual([], strlib.splitWords('', ','))

        # splitWords(blank, value)
        self.assertEqual([], strlib.splitWords(' ', None))   # split by default. ' ' - separator by default, not include
        self.assertEqual([' '], strlib.splitWords(' ', ''))
        self.assertEqual([], strlib.splitWords(' ', ' '))
        self.assertEqual([' '], strlib.splitWords(' ', ','))

        # splitWords(value, value)
        self.assertEqual(['Hello'], strlib.splitWords('Hello', None))
        self.assertEqual(['Hello'], strlib.splitWords('Hello', ''))
        self.assertEqual(['Hello'], strlib.splitWords('Hello', ' '))
        self.assertEqual(['Hello'], strlib.splitWords('Hello', ','))

        # False
        self.assertEqual(['Hello-world'], strlib.splitWords('Hello-world'))                # '-' is not separator

        # True
        self.assertEqual(['Hello', 'world'], strlib.splitWords('Hello world!'))
        self.assertEqual(['Hello', '-', 'world'], strlib.splitWords('Hello - world!'))     # '-' is not separator
        self.assertEqual(['Hello', '-', 'world'], strlib.splitWords('Hello  -  world!'))   # '-' is not separator

        self.assertEqual(['Hello-', 'world'], strlib.splitWords('Hello- world!'))          # '-' is not separator
        self.assertEqual(['Hello', '-world'], strlib.splitWords('Hello -world!'))          # '-' is not separator

        self.assertEqual(['Hello', 'world', 'It', 'is', 'good', 'world'], strlib.splitWords('Hello world! It is good world!'))
        self.assertEqual(['Hello', 'world', 'It', 'is', 'good', 'world'], strlib.splitWords('Hello world! It is good world!', ' !'))
        self.assertEqual(['Hello', 'world', 'It', 'is', 'good', 'world'], strlib.splitWords('Hello world! It is good world!', ' !?.'))
        self.assertEqual(['Hello world', ' It is good world'], strlib.splitWords('Hello world! It is good world!', '!'))

    def test_splitLines(self):

        # splitLines(None)
        self.assertEqual([], strlib.splitLines(None))

        # splitLines(empty)
        self.assertEqual([], strlib.splitLines(''))

        # splitLines(blank)
        self.assertEqual([' '], strlib.splitLines(' '))

        # splitLines(value)
        self.assertEqual(['Hello'], strlib.splitLines('Hello'))

        # split=False
        self.assertEqual(['Hello world! It is good world!'], strlib.splitLines('Hello world! It is good world!'))
        self.assertEqual(['Hello world!\tIt is good world!'], strlib.splitLines('Hello world!\tIt is good world!'))

        # split=True
        self.assertEqual(['Hello world!', 'It is good world!'], strlib.splitLines('Hello world!\rIt is good world!'))        # \r    - OK
        self.assertEqual(['Hello world!', 'It is good world!'], strlib.splitLines('Hello world!\nIt is good world!'))        # \n    - OK
        self.assertEqual(['Hello world!', 'It is good world!'], strlib.splitLines('Hello world!\r\nIt is good world!'))      # \r\n  - OK 

        self.assertEqual(['Hello world!', 'It is good world!'], strlib.splitLines('Hello world!\fIt is good world!'))        # \f    - OK (???)
        self.assertEqual(['Hello world!', 'It is good world!'], strlib.splitLines('Hello world!\vIt is good world!'))        # \v    - OK (???)

        # IMPORTANT !!!
        self.assertEqual(['Hello world!', '', 'It is good world!'], strlib.splitLines('Hello world!\n\rIt is good world!'))  # \n\r  - OK
        self.assertEqual(['Hello world!', '', 'It is good world!'], strlib.splitLines('Hello world!\n\nIt is good world!'))  # \n\n  - OK
        self.assertEqual(['Hello world!', '', 'It is good world!'], strlib.splitLines('Hello world!\r\rIt is good world!'))  # \r\r  - OK

        self.assertEqual(['Hello world!', '', 'It is good world!'], strlib.splitLines('Hello world!\n\r\nIt is good world!')) # \n\r\n - OK
        self.assertEqual(['Hello world!', '', '', 'It is good world!'], strlib.splitLines('Hello world!\n\n\rIt is good world!')) # \n\n\r - OK

    ####

    def test_tokenizeBySeparator_default(self):

        # tokenizeBySeparator(None, value)
        self.assertEqual([], strlib.tokenizeBySeparator(None, None))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ''))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ' '))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ','))

        # tokenizeBySeparator(empty, value)
        self.assertEqual([], strlib.tokenizeBySeparator('', None))
        self.assertEqual([], strlib.tokenizeBySeparator('', ''))
        self.assertEqual([], strlib.tokenizeBySeparator('', ' '))
        self.assertEqual([], strlib.tokenizeBySeparator('', ','))

        # tokenizeBySeparator(value, value)
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', None))   # split by default. ' ' - default separator, include by default
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ''))
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ' '))
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ','))

        # tokenizeBySeparator(value, value)
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', None))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ''))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ' '))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ','))

        # False
        self.assertEqual(['abc'], strlib.tokenizeBySeparator('abc', ','))
        self.assertEqual(['abc:def'], strlib.tokenizeBySeparator('abc:def', ','))

        # True
        self.assertEqual(['abc', ',', 'def', ',', 'xyz'], strlib.tokenizeBySeparator('abc,def,xyz', ','))
        self.assertEqual(['abc', ',', ' def', ',', ' xyz'], strlib.tokenizeBySeparator('abc, def, xyz', ','))

    # base=tokenizeBySeparator_true_false
    # includeAll=True, preserveAll=True
    def test_tokenizeBySeparator_true_true(self):

        # tokenizeBySeparator(None, value, True, True)
        self.assertEqual([], strlib.tokenizeBySeparator(None, None, True, True))
        self.assertEqual([], strlib.tokenizeBySeparator(None, '', True, True))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ' ', True, True))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ',', True, True))

        # tokenizeBySeparator(empty, value, True, True)
        self.assertEqual([], strlib.tokenizeBySeparator('', None, True, True))
        self.assertEqual([], strlib.tokenizeBySeparator('', '', True, True))
        self.assertEqual([], strlib.tokenizeBySeparator('', ' ', True, True))
        self.assertEqual([], strlib.tokenizeBySeparator('', ',', True, True))

        # tokenizeBySeparator(value, value, True, True)
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', None, True, True))    # split by default. ' ' - default separator
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', '', True, True))
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ' ', True, True))
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ',', True, True))

        # tokenizeBySeparator(value, value, True, True)
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', None, True, True))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', '', True, True))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ' ', True, True))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ',', True, True))

        # False
        self.assertEqual(['abc'], strlib.tokenizeBySeparator('abc', ',', True, True))
        self.assertEqual(['abc:def'], strlib.tokenizeBySeparator('abc:def', ',', True, True))

        # True
        self.assertEqual(['abc', ',', 'def', ',', 'xyz'], strlib.tokenizeBySeparator('abc,def,xyz', ',', True, True))
        self.assertEqual([',', 'abc', ',', 'def', ',', 'xyz'], strlib.tokenizeBySeparator(',abc,def,xyz', ',', True, True))
        self.assertEqual([',', 'abc', ',', 'def', ',', 'xyz', ','], strlib.tokenizeBySeparator(',abc,def,xyz,', ',', True, True))

        self.assertEqual(['abc', ',', ' def', ',', ' xyz'], strlib.tokenizeBySeparator('abc, def, xyz', ',', True, True))
        self.assertEqual([',', ' abc', ',', ' def', ',', ' xyz'], strlib.tokenizeBySeparator(', abc, def, xyz', ',', True, True))
        self.assertEqual([',', ' abc', ',', ' def', ',', ' xyz', ','], strlib.tokenizeBySeparator(', abc, def, xyz,', ',', True, True))

    # includeAll=True, preserveAll=False
    def test_tokenizeBySeparator_true_false(self):

        # tokenizeBySeparator(None, value, True, False)
        self.assertEqual([], strlib.tokenizeBySeparator(None, None, True, False))
        self.assertEqual([], strlib.tokenizeBySeparator(None, '', True, False))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ' ', True, False))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ',', True, False))

        # tokenizeBySeparator(empty, value, True, False)
        self.assertEqual([], strlib.tokenizeBySeparator('', None, True, False))
        self.assertEqual([], strlib.tokenizeBySeparator('', '', True, False))
        self.assertEqual([], strlib.tokenizeBySeparator('', ' ', True, False))
        self.assertEqual([], strlib.tokenizeBySeparator('', ',', True, False))

        # tokenizeBySeparator(value, value, True, False)
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', None, True, False))    # split by default. ' ' - default separator
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', '', True, False))
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ' ', True, False))
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ',', True, False))

        # tokenizeBySeparator(value, value, True, False)
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', None, True, False))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', '', True, False))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ' ', True, False))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ',', True, False))

        # False
        self.assertEqual(['abc'], strlib.tokenizeBySeparator('abc', ',', True, False))
        self.assertEqual(['abc:def'], strlib.tokenizeBySeparator('abc:def', ',', True, False))

        # True
        self.assertEqual(['abc', ',', 'def', ',', 'xyz'], strlib.tokenizeBySeparator('abc,def,xyz', ',', True, False))
        self.assertEqual([',', 'abc', ',', 'def', ',', 'xyz'], strlib.tokenizeBySeparator(',abc,def,xyz', ',', True, False))
        self.assertEqual([',', 'abc', ',', 'def', ',', 'xyz', ','], strlib.tokenizeBySeparator(',abc,def,xyz,', ',', True, False))

        self.assertEqual(['abc', ',', ' def', ',', ' xyz'], strlib.tokenizeBySeparator('abc, def, xyz', ',', True, False))
        self.assertEqual([',', ' abc', ',', ' def', ',', ' xyz'], strlib.tokenizeBySeparator(', abc, def, xyz', ',', True, False))
        self.assertEqual([',', ' abc', ',', ' def', ',', ' xyz', ','], strlib.tokenizeBySeparator(', abc, def, xyz,', ',', True, False))

    # includeAll=False, preserveAll=False
    def test_tokenizeBySeparator_false_false(self):

        # tokenizeBySeparator(None, value, False, False)
        self.assertEqual([], strlib.tokenizeBySeparator(None, None, False, False))
        self.assertEqual([], strlib.tokenizeBySeparator(None, '', False, False))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ' ', False, False))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ',', False, False))

        # tokenizeBySeparator(empty, value, False, False)
        self.assertEqual([], strlib.tokenizeBySeparator('', None, False, False))
        self.assertEqual([], strlib.tokenizeBySeparator('', '', False, False))
        self.assertEqual([], strlib.tokenizeBySeparator('', ' ', False, False))
        self.assertEqual([], strlib.tokenizeBySeparator('', ',', False, False))

        # tokenizeBySeparator(value, value, False, False)
        self.assertEqual([], strlib.tokenizeBySeparator(' ', None, False, False))    # split by default. ' ' - default separator, not include
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', '', False, False))
        self.assertEqual([], strlib.tokenizeBySeparator(' ', ' ', False, False))     # not include separator ' '
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ',', False, False))

        # tokenizeBySeparator(value, value, False, False)
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', None, False, False))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', '', False, False))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ' ', False, False))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ',', False, False))

        # False
        self.assertEqual(['abc'], strlib.tokenizeBySeparator('abc', ',', False, False))
        self.assertEqual(['abc:def'], strlib.tokenizeBySeparator('abc:def', ',', False, False))

        # True
        self.assertEqual(['abc', 'def', 'xyz'], strlib.tokenizeBySeparator('abc,def,xyz', ',', False, False))         # not include separator ','
        self.assertEqual(['abc', 'def', 'xyz'], strlib.tokenizeBySeparator(',abc,def,xyz', ',', False, False))        # not include separator ','
        self.assertEqual(['abc', 'def', 'xyz'], strlib.tokenizeBySeparator(',abc,def,xyz,', ',', False, False))       # not include separator ','

        self.assertEqual(['abc', ' def', ' xyz'], strlib.tokenizeBySeparator('abc, def, xyz', ',', False, False))      # not include separator ','
        self.assertEqual([' abc', ' def', ' xyz'], strlib.tokenizeBySeparator(', abc, def, xyz', ',', False, False))   # not include separator ','
        self.assertEqual([' abc', ' def', ' xyz'], strlib.tokenizeBySeparator(', abc, def, xyz,', ',', False, False))  # not include separator ','

    # includeAll=False, preserveAll=True
    def test_tokenizeBySeparator_false_true(self):

        # tokenizeBySeparator(None, value, False, True)
        self.assertEqual([], strlib.tokenizeBySeparator(None, None, False, True))
        self.assertEqual([], strlib.tokenizeBySeparator(None, '', False, True))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ' ', False, True))
        self.assertEqual([], strlib.tokenizeBySeparator(None, ',', False, True))

        # tokenizeBySeparator(empty, value, False, True)
        self.assertEqual([], strlib.tokenizeBySeparator('', None, False, True))
        self.assertEqual([], strlib.tokenizeBySeparator('', '', False, True))
        self.assertEqual([], strlib.tokenizeBySeparator('', ' ', False, True))
        self.assertEqual([], strlib.tokenizeBySeparator('', ',', False, True))

        # tokenizeBySeparator(value, value, False, True)
        self.assertEqual([], strlib.tokenizeBySeparator(' ', None, False, True))      # split by default. ' ' - default separator, not include
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', '', False, True))
        self.assertEqual(['', ''], strlib.tokenizeBySeparator(' ', ' ', False, True)) # not include separator ' ', but preserveAll = True (???)
        self.assertEqual([' '], strlib.tokenizeBySeparator(' ', ',', False, True))

        # tokenizeBySeparator(value, value, False, True)
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', None, False, True))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', '', False, True))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ' ', False, True))
        self.assertEqual(['a'], strlib.tokenizeBySeparator('a', ',', False, True))

        # False
        self.assertEqual(['abc'], strlib.tokenizeBySeparator('abc', ',', False, True))
        self.assertEqual(['abc:def'], strlib.tokenizeBySeparator('abc:def', ',', False, True))

        # True
        self.assertEqual(['abc', 'def', 'xyz'], strlib.tokenizeBySeparator('abc,def,xyz', ',', False, True))                 # not include separator ','
        self.assertEqual(['', 'abc', 'def', 'xyz'], strlib.tokenizeBySeparator(',abc,def,xyz', ',', False, True))            # not include separator ','
        self.assertEqual(['', 'abc', 'def', 'xyz', ''], strlib.tokenizeBySeparator(',abc,def,xyz,', ',', False, True))       # not include separator ','

        self.assertEqual(['abc', ' def', ' xyz'], strlib.tokenizeBySeparator('abc, def, xyz', ',', False, True))              # not include separator ','
        self.assertEqual(['', ' abc', ' def', ' xyz'], strlib.tokenizeBySeparator(', abc, def, xyz', ',', False, True))       # not include separator ','
        self.assertEqual(['', ' abc', ' def', ' xyz', ''], strlib.tokenizeBySeparator(', abc, def, xyz,', ',', False, True))  # not include separator ','


if __name__ == '__main__':
    unittest.main()
