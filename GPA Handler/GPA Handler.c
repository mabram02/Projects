/*========================================================================
Michael Bramer
CECS 130-02
Assignment 05_2
=========================================================================*/
# include <stdio.h>
int main()
{
	int i = 0;
	float iArray[30];
	int response;
	int number_of_grades = 0;
	float sum_of_grades = 0;
	float Avg = 0;
	for (i =1 ; i <= 30; ++i)
	{
		printf("\n::::::::::::::Options::::::::::::::::::\n");
		printf("1.)Enter a GPA (Can hold up to 30 students)?\n");
		printf("2.)Calculate the average GPA of the class?\n");
		printf("Enter your selection of 1 or 2:");
		scanf("%d", &response);
		if (response == 1 || response == 2) {
			if (response == 1){
				printf("Enter the GPA for student #%d:", i);
    			scanf("%f", &iArray[i]);
    			number_of_grades += 1;	
				sum_of_grades += iArray[i];	
			}
			else{
				Avg = sum_of_grades / (i-1) ;
				printf("The average class GPA is: %f", Avg);
				i = 31;	
			}	
		}
		else
		{
			i--;
			printf("You did not select a valid choice! Please choose again!\n\n");	
		}
    	
	}
	
	return 0;
}
