package com.example.fabio.brasileiroapi.model;

import java.math.BigInteger;

public interface Ranking {
	BigInteger getSeq();
	String getNome();
	BigInteger getPontuacao();
	BigInteger getCravadas();
	BigInteger getColocacao();
}
