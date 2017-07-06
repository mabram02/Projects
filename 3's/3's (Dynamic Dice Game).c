/*========================================================================
:::::::::::::::::::::::::3's by Michael Bramer::::::::::::::::::::::::::::
=========================================================================*/
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

/*::::::::::::::::::::::::::::::Structure::::::::::::::::::::::::::::::::*/
typedef struct player {
	char user_name[30];
	int left;
	int score;
} user;

/*:::::::::::::::::::::::::Prototyped Functions:::::::::::::::::::::::::::*/
void rules ();
int TossDie (int *, int); 
int select_die(int *, int , int , int *);
int ClearArray(int *, int);
int FindWinner(int *, int);
void Dice_Boarder();
void pause (int);
int ValueArrayCreate(int *, int *, int);
void swap(int *, int *);

/*:::::::::::::::::::::::::::Called Functions::::::::::::::::::::::::::::::*/
int main(){
	//==========================Variable Declarations==================================
	int x;//Used as a counter variable
	int i;//Used to establish the total number of players
	int j;//I don't know what this does
	int count; //Used as a working variable for diplay and incrementation purposes
	int data[2] = {0,0};//This is an Array that Hold 2 Values: The Total Number of Points Accumulated and Number of Dice Picked Up Durring a Roll
	//  values[users_playing[x].left]; This is an Array Defined Dynamically Below in order to Hold the Value of All the Dice Rolled 
	srand(time(NULL));
	//=================================================================================
	
	//=============================Player Setting======================================
	rules();
	printf(":::::::::::::::::::::::::Player Select::::::::::::::::::::::::::");
	printf("\n\nHow many players will be playing?\n");
	printf("\tEnter number of players here: ");
	scanf("%d", &i);
	user users_playing[i];
	printf("\nEach member will now enter their name:");	
	for(x=0; x != i; x++)//This Loop Creates Variables for Each Player Instance 
	{
		count = x + 1;
		printf("\n\tPlayer #%d Enter Your Name (up to 30 characters): ", count);
		scanf("%s", users_playing[x].user_name);
		users_playing[x].left = 5;//Sets the number of dice to 5
		users_playing[x].score = 0;//Sets the initial score to 0	
	}
	printf("\n\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	//================================================================================
	
	//=============================Player Turns=======================================
	printf("\n\n:::::::::::::::::::::::::::Let's Begin::::::::::::::::::::::::::");

	for(x = 0; x < i; x++){
		printf("\nIt is %s's Turn:", users_playing[x].user_name);
		if(users_playing[x].left > 0){
			printf("\n\tYou have %d dice to roll\t", users_playing[x].left);
			int values[users_playing[x].left];//Holds the Values for Each Die Rolled
			TossDie(values, users_playing[x].left);
			printf("\n\tYour Current Score is: %d ", users_playing[x].score);
			select_die(values, users_playing[x].left, users_playing[x].score, data);
			users_playing[x].score += data[0];//data[0] holds the total number of points accumulated from their pick up
			users_playing[x].left -= data[1];//data[1] holds the total number of dice picked up
			ClearArray(data, 2);
			printf("\n\tYour score after this roll is %d\n", users_playing[x].score);
			x--;//insures that x is staying at the value 
		}
		else{
			Dice_Boarder();
			printf("\n\t\t\t%s, Your turn has ended.\n\t\t\tYour total score is: %d", users_playing[x].user_name, users_playing[x].score);
			Dice_Boarder();
		}
	}
	//=================================================================================
	
	//============================Display the Winner===================================
	//Make a function that returns an int to tell who won. 
	//Ideally it will sort the array with the play scores while also sorting an array with the corresponding number and return the corresponding number
	int PlayerScore[i];
	for(x= 0; x < i; x++){
		PlayerScore[x] = users_playing[x].score;
	}
	x = FindWinner(PlayerScore,i);
	Dice_Boarder();
	printf("\n\t\t\tThe Winner is: %s\n\n\t\t\tWith a score of: %d\n\n\t\t\tThanks for Playing!", users_playing[x].user_name, users_playing[x].score);
	Dice_Boarder();	
	return 0;
	//=================================================================================	
}

/*::::::::::::::::::::::::::::Defined Functions Used in Main::::::::::::::::::::::::::*/

//rules() Displays a Series of Print Statements Telling The User The Rules of the Game
void rules()
{
	printf("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	printf("\n\n\t\tWelcome to the Dice Game: 3's!");
	printf("\n\n::::::::::::::::::::::::::::::Rules:::::::::::::::::::::::::::::");
	printf("\n\n1.) Each player starts with five dice");
	printf("\n\n2.) Each roll you must pick up one or more dice to contribute to your score.");
	printf("\n\n3.) The value on the face is the number added to your score except for three.");
	printf("\n\n4.) If you pick up a three that die contributes zero points to your score.");
	printf("\n\n5.) The player with the lowest score at the end of the game wins.");
	printf("\n\n::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n\n");
}


//TossDie() Yields an Array of 'X' Randomized Numbers
int TossDie(int *p, int size){
	int i;
	for (i = 0; i < size; i++)
	{
		p[i] = (rand() % 6)+1;
	}
	return *p;
}


//select_die() Yields an Array Holding 'The Number of Die Picked Up' and 'Total Number of Points to Add to Score'
int select_die(int *values, int left, int score, int *data){
	/*===========================Variable Declarations================================*/
	int i; //Used as a counter variable
	int loop; //Used in the For Loop to Tell When the User Will Leave the Loop
	int add_to_score = 0; //Used to Tally Up All of the Points to Add to the Data Array to Add to Score
	int subtract_to_left = 0; //Used to Tally Up All of the Points to Add to the Data Array to Subtract from the Number of Dice Left
	int x; //Used as an Input Variable for Which Die the User Would Like to Pick Up
	int j = left;	
	int true_values[j];//An Array that Will Mirror the Values Array while Also Holding the Point Value for Each Die
	/*=================================================================================*/
	
	/*==========================Displays Roll==========================================*/
	Dice_Boarder();
	for(i = 0; i < left; i++){
		pause(1.6 + 0.2*i);
		printf("\n\t\t\tFor Die #%d You Rolled a %d", i + 1, values[i]);
	}
	Dice_Boarder();
	/*=================================================================================*/
	
	/*=========================Selection of Die========================================*/
	ValueArrayCreate(values,true_values, j);
	printf("\n\tEnter the corresponding number to the dice you wish to pick up\n \t(Enter 0 to Reroll the Remaining Die):\n");
	for(loop = 0; loop < 1; loop++){
		if(subtract_to_left == left){
			printf("\t\tYou are out of dice to roll\n");
			subtract_to_left += 1;
			loop = 2;
		}
		else{
			printf("\t\tI would like to pick up die # ");
			scanf("%d", &x);
			x -= 1;
			if(x + 1 > left || x + 1 < 0){
				printf("\t\tSorry, you have selected a die that does not exsist\n");
			}
			else{
				if(true_values[x] == -10){
					printf("\t\tSorry, you have already selected that die\n");
				}
				else{
					if(x + 1 == 0){
						if(subtract_to_left == 0){
							printf("\t\tSorry, you must select at least one die\n");
						}
						else{
							loop = 2;
						}
					}
					else{
						add_to_score += true_values[x];
						subtract_to_left += 1;
						true_values[x] = -10; //used to detect repeated pick ups
					}	
				}
			}
		}
		loop--;
	}
	/*=================================================================================*/
	
	/*===================Translating The Roll Data into the Array======================*/	
	data[0] += add_to_score;//used to set the score
	data[1] += (subtract_to_left);//used to set the number of dice left
	return *data;
	/*=================================================================================*/
}


//ClearArray() Sets All the Values in the Array to Zero
int ClearArray(int *p, int size){
	int i;
	for(i=0; i < size; i++){
		p[i] = 0;
	}
	return *p;
}


//FindWinner() Returns the index number of the winning player
int FindWinner(int *scores, int size){
	int x = 0;//used for incrimentation
	int i, j;//used for BubbleSort Loop
	int winner;
	int index[size];
	for(x = 0; x < size; x++){
		index[x] = x;
	}
	for (i = 0; i < size-1; i++){    
		for (j = 0; j < size-i-1; j++){
			if (scores[j] > scores[j+1]){
              swap(&scores[j], &scores[j+1]);
              swap(&index[j], &index[j+1]);
			}
		} 
	}
	winner = index[0];
	for( i = 0; i < size; i++){
		if(winner < index [i]){
			winner = index [i];
		}
	}
	winner = index[0];
	return winner;
}

/*:::::::::::::::::::Defined Functions Used in Other Functions:::::::::::::::::::::*/

//Dice_Boarder() Displays a Boarder
void Dice_Boarder(){
	printf("\n\n\t        =========================================");
}


//pause() Pauses the Code for 'X' amount of Seconds
void pause(int inNum)
{
	int CurrentTime = 0;
	int ElapsedTime = 0;
	CurrentTime = time(NULL);
	do {
		ElapsedTime = time(NULL);
	} while ((ElapsedTime - CurrentTime) < inNum);
}	


//ValueArrayCreate() Creates an Array for the Point Value of Each Die Rolled 
int ValueArrayCreate(int *data, int *empty_array, int size){
	int i, x;
	for (i = 0; i < size; i++){
		x = data[i];
		if (x == 3){
			empty_array[i] = 0;
		}	
		else{
			empty_array[i] = x;
		}
	}
	return *empty_array;
}


//swap() Swaps values within an Array for sorting purposes
void swap(int *xp, int *yp){
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}
	
