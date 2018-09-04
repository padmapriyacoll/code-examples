#!/bin/bash
# Prints top ten directories by size.
Dir_name="/home /var/log"
echo "Top Ten Directories"
for dir in $Dir_name;
do
   echo "The $dir Directory:"
   du -S $dir|sort -rn |sed '{11,$D; =}' |sed 'N; s/\n/ /' |awk '{printf $1 "\t" "\t" $2 "\t" $3 "\r\n"}'
done
exit
