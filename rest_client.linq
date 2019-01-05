<Query Kind="Program">
  <Reference Relative="Newtonsoft.Json.dll">D:\0 Cloned Repos [GitHub]\bio-med\Newtonsoft.Json.dll</Reference>
  <Reference>&lt;RuntimeDirectory&gt;\System.Net.Http.dll</Reference>
  <Namespace>System.Net.Http</Namespace>
</Query>


void Main()
{
	String base_url = "http://localhost:8080/";
	
	String api = "api";
	String method = "api/aaa";
	String states = "states";
	String dependencies = "dependencies?target={0}";
	String evaluate = "evaluate";

	

{
	System.Net.HttpWebRequest request = (System.Net.HttpWebRequest)System.Net.WebRequest.Create(base_url+String.Format(dependencies, "autism")); //"autisma"));
	       		
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
		   
		   {
	System.Net.HttpWebRequest request = (System.Net.HttpWebRequest)System.Net.WebRequest.Create(base_url+String.Format(evaluate)); //"autisma"));
	       		Object obj = new{ Target = "autism",
				Data = new Dictionary<string, Double>
{
	{"Abnormal Body Posturing",						1},
    {"Abnormal Facial Expressions",					0},
    {"Avoidance of Eye Contact",					1},
    {"Poor Eye Contact",							1},
    {"Delay in Learning to Speak",					0},
    {"Intense Focus on One Topic",					0},
    {"Lack of Empathy",								0},
    {"Lack of Understanding Social Cues",			1},
    {"Repetitive Movements",						1},
    {"Social Withdrawal",							1},

}};
			var content = Newtonsoft.Json.JsonConvert.SerializeObject(obj);
			var data = Encoding.ASCII.GetBytes(content);
			
			request.Method = "POST";
			request.ContentType = "application/json";
			using (var stream = request.GetRequestStream())
			{
			 	stream.Write(data, 0, data.Length);
			}
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
}


        // TODO (v-nipej): remove after testing is done.
        private static string FormatJson(string json)
        {
            dynamic parsedJson = Newtonsoft.Json.JsonConvert.DeserializeObject(json);
            return Newtonsoft.Json.JsonConvert.SerializeObject(parsedJson, Newtonsoft.Json.Formatting.Indented);
        }
