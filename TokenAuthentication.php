<?php

namespace App\Helpers;

class TokenAuthentication
{
	public function validateToken($token)
	{
		if (strlen($token) != 44) {
			return false;
		} else {
			$decryptedToken = "";
			for ($i = 0; $i <= 43; $i++) {
				$decryptedToken = $decryptedToken . $this->decryptValue(substr($token, $i, 1));
			}
			$orderOfSequence = substr($decryptedToken, 20, 1);
			$logicText = substr($decryptedToken, 24, 20);
			$generatedText = "";
			switch ($orderOfSequence) {
				case "s";
					$generatedText = $generatedText . substr($logicText, 10, 5);
					$generatedText = $generatedText . substr($logicText, 0, 5);
					$generatedText = $generatedText . substr($logicText, 15, 5);
					$generatedText = $generatedText . substr($logicText, 5, 5);
					break;
				case "j";
					$generatedText = $generatedText . substr($logicText, 0, 5);
					$generatedText = $generatedText . substr($logicText, 10, 5);
					$generatedText = $generatedText . substr($logicText, 15, 5);
					$generatedText = $generatedText . substr($logicText, 5, 5);
					break;
				case "i";
					$generatedText = $generatedText . substr($logicText, 10, 5);
					$generatedText = $generatedText . substr($logicText, 0, 5);
					$generatedText = $generatedText . substr($logicText, 5, 5);
					$generatedText = $generatedText . substr($logicText, 15, 5);
					break;
				case "w";
					$generatedText = $generatedText . substr($logicText, 5, 5);
					$generatedText = $generatedText . substr($logicText, 10, 5);
					$generatedText = $generatedText . substr($logicText, 0, 5);
					$generatedText = $generatedText . substr($logicText, 15, 5);
					break;
			}
			$firstChar = substr($decryptedToken, 0, 20);
			$lastChar = $generatedText;
			if ($firstChar != $lastChar) {
				return false;
			} else {
				return true;
			}
		}
	}

	private function decryptValue($value)
	{
		switch ($value) {
			case "&":
				return "0";
			case "#":
				return "1";
			case "(":
				return "2";
			case ")":
				return "3";
			case "!":
				return "4";
			case "@":
				return "5";
			case "$":
				return "6";
			case "^":
				return "7";
			case "*":
				return "8";
			case ":":
				return "9";
			case ";":
				return "s";
			case "{":
				return "j";
			case "}":
				return "i";
			case "[":
				return "w";
			default:
				return "o";
		}
	}
}
