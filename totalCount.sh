#!/usr/bin/env bash
THUGS=/Users/indranildey/code/cluster-compare/thugs
NOTORIOUS=/Users/indranildey/code/cluster-compare/notorious
DIFF=/Users/indranildey/code/cluster-compare/DIFF_OUTPUT
mkdir ${DIFF}
for f in `ls ${THUGS}`
do
    echo "comparing between ${THUGS}/${f} and ${NOTORIOUS}/${f}" >> ${DIFF}/"${f}.diff.txt"
    echo "======================================================" >> ${DIFF}/"${f}.diff.txt"
    diff ${THUGS}/${f} ${NOTORIOUS}/${f} >> ${DIFF}/"${f}.diff.txt"
    echo "======================================================" >> ${DIFF}/"${f}.diff.txt"
done

