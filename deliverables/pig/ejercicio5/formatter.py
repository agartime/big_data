#########################################################
# Antonio Gonzalez Artime - Pig UDF - Bag item counter  #
#########################################################

@outputSchema("output:chararray") 
def custom_output(id,page_count,product_count):
  return "("+id+","+str(page_count)+","+str(product_count)+")";
