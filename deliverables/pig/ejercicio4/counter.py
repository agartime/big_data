#########################################################
# Antonio Gonzalez Artime - Pig UDF - Bag item counter  #
#########################################################

from com.xhaus.jyson import JysonCodec as json

@outputSchema("count:int") 
def count_bag_items(json_str):
  return len(json.loads(json_str))
