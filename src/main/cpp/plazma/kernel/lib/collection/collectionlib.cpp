#include <string>
#include <map>

namespace collectionlib {

  bool containsKey(std::map<std::string, std::string> &_map, const std::string& key) {
    return _map.find(key) != _map.end();
  }

}
