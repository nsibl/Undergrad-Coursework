/* Noah sibley
   N01512207
   1/23/2023 */

#include <stdio.h>

int main()
{
    int yards = 0;
    int touchDown = 100;
    float yardsPerSec = 0.0;
    
    printf("Where on the field was the ball caught?\n ");
    scanf("%d", &yards);

    printf("How many yards per second is the player running?\n");
    scanf("%f", &yardsPerSec);

    int yardsLeft = touchDown - yards;
    float timeToScore = yardsLeft / yardsPerSec;

    printf("The time it will take to score is: %.2f seconds", timeToScore);

    return 0;

}
