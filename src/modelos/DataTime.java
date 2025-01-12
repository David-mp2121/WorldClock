package modelos;

import com.google.gson.annotations.SerializedName;

public class DataTime {
	@SerializedName("datetime")
	public String dataHora;
	@SerializedName("timezone")
	public String timeZone;
	@SerializedName("utc_offset")
	public String utc;
	@SerializedName("error")
	public String erro;

	public String getDataHora() {
		return dataHora;
	}

	public String getTimezone() {
		return timeZone;
	}

	public String getUtc() {
		return utc;
	}

	@Override
	public String toString() {
		return "Região : " + getTimezone() + " ,  Data e Hora : " + getDataHora() + " , UTC : " + getUtc();
	}

	public String dataEHoraconverter() {

		String hora = this.dataHora.substring(this.dataHora.indexOf("T") + 1,
				this.dataHora.indexOf(".", this.dataHora.indexOf("T")));
		String data = this.dataHora.substring(0,10);
		
		

		return "Data : " +data +", Hora : "+hora;
	}

}
