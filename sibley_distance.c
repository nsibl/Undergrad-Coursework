/* Noah Sibley
   N01512207
   1/23/2023 */

#include <stdio.h>
#include <math.h>
int main ()
{
    float x1;
    float y1;
    float x2;
    float y2;
    
    printf("Input X1 and Y1: \n");
    scanf("%f %f", &x1, &y1);

    printf("Input X2 and Y2: \n");
    scanf("%f %f", &x2, &y2);

    printf("Point A: %.2f, %.2f\n", x1, y1);
    printf("Point B: %.2f, %.2f\n",x2, y2);

    float distance = ((x2-x1)*(x2-x1)) + ((y2-y1)*(y2-y1));


    printf("The distance between the two points is: %.2f units", sqrt(distance));


return 0;

}
