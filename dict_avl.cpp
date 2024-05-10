#include <iostream>
#include <string>
using namespace std;

class AVLDictionary {
private:
    struct Node {
        string word, meaning;
        Node *left = NULL, *right = NULL;
        int height = 1;

        Node(const string& w, const string& m) : word(w), meaning(m) {}
    };

    Node* root = NULL;

public:
    void insert(const string& w, const string& m) {
        root = insertRecursive(root, w, m);
    }

    void remove(const string& w) {
        root = removeRecursive(root, w);
    }

    void update(const string& w, const string& m) {
        root = updateRecursive(root, w, m);
    }

    void displayAscending() {
        inorderTraversal(root);
    }

    void displayDescending() {
        reverseInorderTraversal(root);
    }

    int search(const string& w) {
        return searchRecursive(root, w, 0);
    }

private:
    Node* insertRecursive(Node* n, const string& w, const string& m) {
        if (!n) return new Node(w, m);
        if (w < n->word) n->left = insertRecursive(n->left, w, m);
        else if (w > n->word) n->right = insertRecursive(n->right, w, m);
        return balance(n);
    }

    Node* removeRecursive(Node* n, const string& w) {
        if (!n) return NULL;
        if (w < n->word) n->left = removeRecursive(n->left, w);
        else if (w > n->word) n->right = removeRecursive(n->right, w);
        else {
            if (!n->left || !n->right) {
                Node* temp = n->left ? n->left : n->right;
                if (!temp) temp = n, n = NULL;
                else *n = *temp;
                delete temp;
            } else {
                Node* temp = findMin(n->right);
                n->word = temp->word, n->meaning = temp->meaning;
                n->right = removeRecursive(n->right, temp->word);
            }
        }
        if (!n) return NULL;
        return balance(n);
    }

    Node* updateRecursive(Node* n, const string& w, const string& m) {
        if (!n) return NULL;
        if (w < n->word) n->left = updateRecursive(n->left, w, m);
        else if (w > n->word) n->right = updateRecursive(n->right, w, m);
        else n->meaning = m;
        return n;
    }

    Node* balance(Node* n) {
        updateHeight(n);
        int balanceFactor = getBalance(n);
        if (balanceFactor > 1) {
            if (getBalance(n->left) < 0) n->left = rotateLeft(n->left);
            return rotateRight(n);
        }
        if (balanceFactor < -1) {
            if (getBalance(n->right) > 0) n->right = rotateRight(n->right);
            return rotateLeft(n);
        }
        return n;
    }

    int getBalance(Node* n) {
        return (n ? getHeight(n->left) - getHeight(n->right) : 0);
    }

    void updateHeight(Node* n) {
        if (n) n->height = max(getHeight(n->left), getHeight(n->right)) + 1;
    }

    int getHeight(Node* n) {
        return n ? n->height : 0;
    }

    Node* rotateRight(Node* y) {
        Node* x = y->left;
        Node* T = x->right;
        x->right = y;
        y->left = T;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node* rotateLeft(Node* x) {
        Node* y = x->right;
        Node* T = y->left;
        y->left = x;
        x->right = T;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    void inorderTraversal(Node* n) {
        if (n) {
            inorderTraversal(n->left);
            cout << n->word << ": " << n->meaning << endl;
            inorderTraversal(n->right);
        }
    }

    void reverseInorderTraversal(Node* n) {
        if (n) {
            reverseInorderTraversal(n->right);
            cout << n->word << ": " << n->meaning << endl;
            reverseInorderTraversal(n->left);
        }
    }

    int searchRecursive(Node* n, const string& w, int c) {
        if (!n) return -1;
        if (w == n->word) {
            cout << "Word: " << n->word << ", Meaning: " << n->meaning << endl;
            return c + 1;
        }
        return w < n->word ? searchRecursive(n->left, w, c + 1) : searchRecursive(n->right, w, c + 1);
    }

    Node* findMin(Node* n) {
        return n->left ? findMin(n->left) : n;
    }
};

int main() {
    AVLDictionary dict;
    int numWords;
    cout << "Enter the number of words you want to add: ";
    cin >> numWords;

    string word, meaning;
    for (int i = 0; i < numWords; ++i) {
        cout << "Enter word " << i + 1 << ": ";
        cin >> word;
        cout << "Enter meaning for " << word << ": ";
        cin >> meaning;
        dict.insert(word, meaning);
    }

    int choice;
    do {
        cout << "\n1. Remove a word\n2. Update a word\n3. Display dictionary in ascending order\n4. Display dictionary in descending order\n5. Search for a word\n6. Exit\nEnter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter word to remove: ";
                cin >> word;
                dict.remove(word);
                break;
            case 2:
                cout << "Enter word to update: ";
                cin >> word;
                cout << "Enter new meaning: ";
                cin >> meaning;
                dict.update(word, meaning);
                break;
            case 3:
                cout << "Dictionary in ascending order:\n";
                dict.displayAscending();
                break;
            case 4:
                cout << "Dictionary in descending order:\n";
                dict.displayDescending();
                break;
            case 5:
                cout << "Enter word to search: ";
                cin >> word;
                int c;
                c = dict.search(word);
                if (c == -1) cout << "Word not found!" << endl;
                else cout << "Word found in " << c << " comparisons" << endl;
                break;
            case 6:
                cout << "Exiting...\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
                break;
        }
    } while (choice != 6);

    return 0;
}

