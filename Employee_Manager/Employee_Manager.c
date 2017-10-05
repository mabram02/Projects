/*========================================================================
Michael Bramer
CECS 130-02
Assignment 7_01
=========================================================================*/
#include <stdio.h>
#include <stdlib.h>
struct Employee
{
	char id[9];
	float salary;
};
int main()
{
    int countEmployees, i;
    float raise_percentage, raise_factor;
    struct Employee *myEmployees;
    printf("How many Employees do you have?\nNumber of Employees: ");
    scanf("%d", &countEmployees);
    
    myEmployees = (struct Employee *) malloc (countEmployees*sizeof(struct Employee));
    
    if(myEmployees != NULL)
    {
		printf("\n::::::::::Info Input:::::::::::::\n");
		for( i = 0; i < countEmployees; i++)
		{
			printf("Employee ID: ");
			scanf("%s", &myEmployees[i].id);
			printf("Employee Salary: ");
			scanf("%f", &myEmployees[i].salary);
		}
		printf("\nWhat percentage would you like to raise the salaries by?\nPercent Raise: ");
		scanf("%f",&raise_percentage);
		raise_factor = (1 + (1/raise_percentage));	
		for( i = 0; i < countEmployees; i++)
		{
			myEmployees[i].salary *= raise_factor;
		}
		printf("\n::::::::::Updated Employee Info:::::::::::::\n");	
		for( i = 0; i < countEmployees; i++)
		{
			printf("Employee ID: %s\n", myEmployees[i].id);
			printf("Employee Salary: %f\n", myEmployees[i].salary);
		}
    }
    else{
    	printf("Sorry, you do not have enough memory to add the new info");
	}
    
    free(myEmployees);
    return 0;
}

