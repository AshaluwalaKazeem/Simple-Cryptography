import 'dart:math';

final _random = new Random();
int next(int min, int max) => min + _random.nextInt(max - min);

void main() {
  Token token = new Token();
  token.generateToken();
}

class Token {
  void generateToken() {
    int var1 = next(10000, 99999);
    // variable two will have four digit
    int var2 = next(1000, 9999);
    // variable three will have five digit
    int var3 = next(10000, 99999);
    // variable four will have six digit
    int var4 = next(100000, 999999);

    var array = _shuffle();
    var orderOfSequence = "";

    for (int i = 0; i < array.length; i++) {
      switch (array[i]) {
        case 1:
          orderOfSequence = orderOfSequence + "s";
          break;
        case 2:
          orderOfSequence = orderOfSequence + "j";
          break;
        case 3:
          orderOfSequence = orderOfSequence + "i";
          break;
        case 4:
          orderOfSequence = orderOfSequence + "w";
          break;
      }
    }
    String generatedText =
        var1.toString() + var2.toString() + var3.toString() + var4.toString();

    String remainingText = "";
    switch (orderOfSequence[0]) {
      case "s":
        remainingText = remainingText + generatedText.substring(5, 10);
        remainingText = remainingText + generatedText.substring(15, 20);
        remainingText = remainingText + generatedText.substring(0, 5);
        remainingText = remainingText + generatedText.substring(10, 15);
        break;
      case "j":
        remainingText = remainingText + generatedText.substring(0, 5);
        remainingText = remainingText + generatedText.substring(15, 20);
        remainingText = remainingText + generatedText.substring(5, 10);
        remainingText = remainingText + generatedText.substring(10, 15);
        break;
      case "i":
        remainingText = remainingText + generatedText.substring(5, 10);
        remainingText = remainingText + generatedText.substring(10, 15);
        remainingText = remainingText + generatedText.substring(0, 5);
        remainingText = remainingText + generatedText.substring(15, 20);
        break;
      case "w":
        remainingText = remainingText + generatedText.substring(10, 15);
        remainingText = remainingText + generatedText.substring(0, 5);
        remainingText = remainingText + generatedText.substring(5, 10);
        remainingText = remainingText + generatedText.substring(15, 20);
        break;
    }
    String token = generatedText + orderOfSequence + remainingText;
    print("This is the original token code = " + token);
    String encryptedToken = "";
    for (int i = 0; i <= 43; i++) {
      encryptedToken = encryptedToken + _encryptValue(token[i]);
    }
    print("This is the encrypted token code that we will pass to the server\n" +
        encryptedToken); // return this value to server;
  }

  List _shuffle() {
    var items = [1, 2, 3, 4];
    var random = new Random();

    // Go through all elements.
    for (var i = items.length - 1; i > 0; i--) {
      // Pick a pseudorandom number according to the list length
      var n = random.nextInt(i + 1);

      var temp = items[i];
      items[i] = items[n];
      items[n] = temp;
    }

    return items;
  }

  String _encryptValue(String value) {
    switch (value) {
      case "0":
        return "&";
      case "1":
        return "#";
      case "2":
        return "(";
      case "3":
        return ")";
      case "4":
        return "!";
      case "5":
        return "@";
      case "6":
        return "\$";
      case "7":
        return "^";
      case "8":
        return "*";
      case "9":
        return ":";
      case "s":
        return ";";
      case "j":
        return "{";
      case "i":
        return "}";
      case "w":
        return "[";
      default:
        return "~";
    }
  }
}
