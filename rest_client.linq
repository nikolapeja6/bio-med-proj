<Query Kind="Program">
  <Reference Relative="Newtonsoft.Json.dll">D:\0 Cloned Repos [GitHub]\bio-med\Newtonsoft.Json.dll</Reference>
</Query>

void Main()
{
	String base_url = "http://localhost:8085/";
	
	String greeting = "api";
	
	String url = $@"{base_url}{greeting}";

	System.Net.HttpWebRequest request = (System.Net.HttpWebRequest)System.Net.WebRequest.Create(url);
	//System.Net.HttpWebRequest request = (System.Net.HttpWebRequest)System.Net.WebRequest.Create(UrlBaseDatabricks);// + String.Format(urlTableInfo, "default", "default__indexed_table_index_id__", "integer"));
             		
			System.Net.HttpWebResponse response = (System.Net.HttpWebResponse)request.GetResponse();

			var Status = ((System.Net.HttpWebResponse)response).StatusDescription;

            // Get the stream containing all content returned by the requested server.
            var dataStream = response.GetResponseStream();

            // Open the stream using a StreamReader for easy access.
            StreamReader reader = new StreamReader(dataStream);

            // Read the content fully up to the end.
            string responseFromServer = reader.ReadToEnd();

            // Clean up the streams.
            reader.Close();
            dataStream.Close();
            response.Close();

           Console.WriteLine(FormatJson(responseFromServer));
}


        // TODO (v-nipej): remove after testing is done.
        private static string FormatJson(string json)
        {
            dynamic parsedJson = Newtonsoft.Json.JsonConvert.DeserializeObject(json);
            return Newtonsoft.Json.JsonConvert.SerializeObject(parsedJson, Newtonsoft.Json.Formatting.Indented);
        }
