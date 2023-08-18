#include <string>
#include <vector>

namespace numlib {

    int parseInt(const std::string &input);

    float parseFloat(const std::string &input);

    double parseDouble(const std::string &input);

    ////

    // int

    int toInt(const std::string &str);

    int toInt(const std::string &str, int def);

    // float

    float toFloat(const std::string &str);

    float toFloat(const std::string &str, float def);

    // double

    double toDouble(const std::string &str);

    double toDouble(const std::string &str, double def);

    ////

    float randomFloat(float min, float max);

    std::vector<float> splitFloat(const std::string &str, char delim);

    std::vector<std::vector<float>> readFloatArray(std::ifstream &file);

    std::vector<std::vector<float>> readFloatArray(const std::string &fileName);

    void writeFloatArray(std::ofstream &file, std::vector<std::vector<float>> records);

    void writeFloatArray(const std::string &fileName, std::vector<std::vector<float>> records);

    std::vector<std::vector<float>> generateFloatArray(int cols, int rows, float min, float max);

}