#bash script compilation

cd ./polyeventbackend
for var in commun database polyeventbackenddemandereservation polyeventbackenddemandeevenement; do
    cd $var
    ./compilation.sh
    cd ..
done 