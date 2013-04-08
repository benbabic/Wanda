package fr.irit.wanda.security.certificates;

import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

public class CertificateValidator {

	public static boolean checkCertificate(HttpServletRequest request, int id) {
		int certificate = -2;
		X509Certificate[] certChain = (X509Certificate[]) request
				.getAttribute("javax.servlet.request.X509Certificate");

		if (certChain != null) {
			X509Certificate cert = certChain[certChain.length - 1];
			certificate = cert.getPublicKey().hashCode();
		}
		return certificate == id;
	}
	
	public static String getCertificateCommonName(HttpServletRequest request) {
		String certificate = "";
		X509Certificate[] certChain = (X509Certificate[]) request
				.getAttribute("javax.servlet.request.X509Certificate");

		if (certChain != null) {
			X509Certificate cert = certChain[certChain.length - 2];
			certificate = cert.getSubjectDN().getName();
		}
		return certificate;
	}
}
