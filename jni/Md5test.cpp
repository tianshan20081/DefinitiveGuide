//============================================================================
// Name        : test.cpp
// Author      : aoeng zhang
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "MD5.h"

using namespace std;

int main(int argc, char *argv[])
{
    cout << "md5 of 'grape': " << md5("grape")<<endl;
    return 0;
}

