import random

def readFloatArray(fileName):
    file = open(fileName, "r")
    lines = file.readlines()
    array = []
    for line in lines:
        row = line.split(",")
        values = []
        for cell in row:
            values.append(float(cell))
        array.append(values)
    file.close()
    return array

def writeFloatArray(fileName, array):
    file = open(fileName, "w")
    rowCount = len(array)
    text = ""
    lines = []
    for i in range(rowCount):
        row = array[i]
        line = ""
        colCount = len(row)
        for j in range(colCount):
            if (j > 0):
                line += ","
            line += str(row[j])
        lines.append(line + "\n")
        
        #text += line + "\n"
        #file.write(line + "\n")
    #file.write(text)

    file.writelines(lines)
    file.close()

def generateFloatArray(cols, rows, min, max):
    array = []
    for r in range(rows):
        row = []
        for c in range(cols):
            value = round(random.uniform(min, max), 2)
            row.append(value)
        array.append(row)
    return array
