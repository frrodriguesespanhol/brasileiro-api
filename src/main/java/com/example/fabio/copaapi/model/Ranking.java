package com.example.fabio.copaapi.model;

import java.math.BigInteger;

public interface Ranking {
	BigInteger getSeq();
	String getNome();
	BigInteger getPontuacao();
	BigInteger getCravadas();
}
