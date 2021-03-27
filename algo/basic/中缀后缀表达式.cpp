//
// Created by Noble on 27/04/2018.
//
#include <iostream>
#include <algorithm>
using namespace std;
char op_judge(char a,char b)
{
    if(a==b&&(a=='#'||a=='('))
        return '=';
    else if(a=='('||b=='(')
        return '>';

    else if((a=='+'||a=='-')&&(b=='+'||b=='-'))
        return '>';
    else if((a=='*'||a=='/')&&(b=='+'||b=='-'))
        return '>';
    else if((a=='*'||a=='/')&&(b=='*'||b=='/'))
        return '>';
    else if((a=='-'||a=='+')&&(b=='*'||b=='/'))
        return '<';
}
string RPN(string proto)
{
    string op;
    op.push_back('#');
    string ans;
    for(int i =0;i<proto.length();i++)
    {
        switch(op_judge(op.back(),proto[i]))
        {
            case '>':
                break;
            case '<':
                break;
            case '=':
                break;

        }

    }

}

int main()
{
    string str;
    cin>>str;
 	str.push_back('#');
    cout<<str;
    return 0;
}