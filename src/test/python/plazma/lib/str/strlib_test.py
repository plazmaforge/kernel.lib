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
        
        # startsWith(None/empty, None/empty)
        self.assertFalse(strlib.startsWith(None, None))
        self.assertFalse(strlib.startsWith(None, ''))
        self.assertFalse(strlib.startsWith('', None))
        self.assertFalse(strlib.startsWith('', ''))     # important

        # startsWith(None/blank, None/blank)
        self.assertFalse(strlib.startsWith(None, ' '))
        self.assertFalse(strlib.startsWith(' ', None))

        # startsWith(None/value, None/value)
        self.assertFalse(strlib.startsWith(None, '.'))
        self.assertFalse(strlib.startsWith('.', None))

        # startsWith(value/value, value/value)

        # False
        self.assertFalse(strlib.startsWith('a', ''))    # important
        self.assertFalse(strlib.startsWith('a', 'x'))
        self.assertFalse(strlib.startsWith('a', 'xy'))
        self.assertFalse(strlib.startsWith('a', 'xyz'))

        self.assertFalse(strlib.startsWith('ab', ''))   # important
        self.assertFalse(strlib.startsWith('ab', 'x'))
        self.assertFalse(strlib.startsWith('ab', 'xy'))
        self.assertFalse(strlib.startsWith('ab', 'xyz'))

        # True
        self.assertTrue(strlib.startsWith('a', 'a'))
        self.assertTrue(strlib.startsWith('ab', 'a'))
        self.assertTrue(strlib.startsWith('ab', 'ab'))

        self.assertTrue(strlib.startsWith('abc', 'a'))
        self.assertTrue(strlib.startsWith('abc', 'ab'))
        self.assertTrue(strlib.startsWith('abc', 'abc'))

        ##
        self.assertTrue(strlib.startsWith('myfile.txt', 'my'))
        self.assertTrue(strlib.startsWith('myfile.txt', 'myfile'))
        self.assertTrue(strlib.startsWith('myfile.txt', 'myfile.txt'))

    def test_endsWith(self):
        
        # endsWith(None/empty, None/empty)
        self.assertFalse(strlib.endsWith(None, None))
        self.assertFalse(strlib.endsWith(None, ''))
        self.assertFalse(strlib.endsWith('', None))
        self.assertFalse(strlib.endsWith('', ''))     # important

        # endsWith(None/blank, None/blank)
        self.assertFalse(strlib.endsWith(None, ' '))
        self.assertFalse(strlib.endsWith(' ', None))

        # endsWith(None/value, None/value)
        self.assertFalse(strlib.endsWith(None, '.'))
        self.assertFalse(strlib.endsWith('.', None))

        # endsWith(value/value, value/value)

        # False
        self.assertFalse(strlib.endsWith('a', ''))    # imoprtant
        self.assertFalse(strlib.endsWith('a', 'x'))
        self.assertFalse(strlib.endsWith('a', 'xy'))
        self.assertFalse(strlib.endsWith('a', 'xyz'))

        self.assertFalse(strlib.endsWith('ab', ''))   # important
        self.assertFalse(strlib.endsWith('ab', 'x'))
        self.assertFalse(strlib.endsWith('ab', 'xy'))
        self.assertFalse(strlib.endsWith('ab', 'xyz'))

        # True
        self.assertTrue(strlib.endsWith('a', 'a'))
        self.assertTrue(strlib.endsWith('ab', 'b'))
        self.assertTrue(strlib.endsWith('ab', 'ab'))

        self.assertTrue(strlib.endsWith('abc', 'c'))
        self.assertTrue(strlib.endsWith('abc', 'bc'))
        self.assertTrue(strlib.endsWith('abc', 'abc'))

        ##
        self.assertTrue(strlib.endsWith('myfile.txt', 'txt'))
        self.assertTrue(strlib.endsWith('myfile.txt', '.txt'))
        self.assertTrue(strlib.endsWith('myfile.txt', 'myfile.txt'))


if __name__ == '__main__':
    unittest.main()
