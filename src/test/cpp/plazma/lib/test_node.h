#include "plazma/lib/data/node/Node.h"

#include "test_helper.h"

node::Node createNodeObj(const std::string& name) {
  node::Node node = node::Node();
  node.setName(name);
  return node;
}

node::Node* createNode(const std::string& name) {
  node::Node* node = new node::Node();
  node->setName(name);
  return node;
}

node::Node* createNode2(const std::string& name) {
  node::Node node = node::Node();
  node::Node* n = &node; //new node::Node();
  n->setName(name);
  return n;
}

uintptr_t createNodeUintPtr(const std::string& name) {
  node::Node* n = new node::Node();
  n->setName(name);
  return reinterpret_cast<uintptr_t>(n);;
}

/*
long createNode3(string name) {
  node::Node* n = new node::Node();
  n->setName(name);
  return reinterpret_cast<long>(n);;
}

string createNode4(string name) {
  node::Node node = node::Node();
  node::Node* n = &node; // new node::Node();
  n->setName(name);
  long addr = reinterpret_cast<long>(n);;
  return "" + to_string(addr) + "";
}
*/


void test_Node() {
  printHeader("TEST NODE");
  
  node::Node* n1 = new node::Node();
  n1->setName("N1");

  node::Node* n1_1 = new node::Node();
  n1_1->setName("N1_1");
  n1->addChild(n1_1);

  node::Node* n1_2 = new node::Node();
  n1_2->setName("N1_2");
  n1->addChild(n1_2);

  std::cout << "Node: "  << n1->toString() << std::endl;

  delete n1;

  createNode("N11");

}

// 2. TEST NODE
void test_node_all() {
    test_Node();
}
