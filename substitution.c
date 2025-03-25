#include <cs50.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void printPrompt();
void printError();
int validateKey(string str);
string convertToUppercase(string str);
string cipher(string plain, string key);

int main(int argc, char **argv)
{
    if (argc != 2)
    {
        printPrompt();
        return 1;
    }

    string key = convertToUppercase(argv[1]);

    if (validateKey(key) != 1)
    {
        printError();
        return 1;
    }
    string plain = get_string("plaintext: ");

    string cipherText = cipher(plain, argv[1]);

    printf("ciphertext: %s\n", cipherText);

    free(key);
    free(cipherText);

    return 0;
}

string cipher(string plain, string key)
{
    int i = 0;
    int seq;
    string cipherText = malloc(strlen(plain) + 1);
    while (plain[i] != '\0')
    {
        if (isalpha(plain[i]))
        {
            if (islower(plain[i]))
            {
                seq = 25 - ('z' - plain[i]);
                cipherText[i] = tolower(key[seq]);
            }
            else
            {
                seq = 25 - ('Z' - plain[i]);
                cipherText[i] = toupper(key[seq]);
            }
        }
        else
        {
            cipherText[i] = plain[i];
        }
        i++;
    }
    cipherText[i] = '\0';
    return cipherText;
}

void printPrompt()
{
    printf("Usage: ./substitution key\n");
}

void printError()
{
    printf("Key must contain 26 characters.\n");
}

string convertToUppercase(char *str)
{
    int i = 0;
    string upText = malloc(strlen(str) + 1);
    while (str[i] != '\0')
    {
        upText[i] = toupper(str[i]);
        i++;
    }
    upText[i] = '\0';
    return upText;
}

int validateKey(string str)
{
    // check if all alphabetical && count
    int count = 0;
    int i = 0;
    int j = 0;
    while (str[i] != '\0')
    {

        if (i > 25 || !isalpha(str[i]))
        {
            return 0;
        }
        j = i + 1;
        while (str[j] != '\0')
        {
            if (str[i] == str[j])
            {
                return 0;
            }
            j++;
        }
        i++;
    }

    if (i < 25)
    {
        return 0;
    }
    return 1;
}
