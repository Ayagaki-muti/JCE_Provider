package com.No_N_Name.jce.jna.struct2.dsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.No_N_Name.jce.jna.api.CryptoException;
import com.No_N_Name.jce.jna.struct2.IDSArefPrivateKey;
import com.No_N_Name.jce.provider.utils.BytesUtil;
import com.sun.jna.Structure;


public class DSArefPrivateKeyEx extends Structure implements IDSArefPrivateKey {
	  public int bits;
	    public byte[] p = new byte[512];
	    public byte[] q = new byte[32];
	    public byte[] g = new byte[512];
	    public byte[] pubkey = new byte[512];
	    public byte[] privkey = new byte[32];

	    public DSArefPrivateKeyEx() {
	    }

	    public DSArefPrivateKeyEx(int bits, byte[] p, byte[] q, byte[] g, byte[] pubkey, byte[] privkey) {
	        if (p.length > 513) {
	            throw new RuntimeException("p length[ " + p.length + " ]");
	        } else {
	            this.bits = bits;
	            System.arraycopy(p, 0, this.p, 512 - p.length, p.length);
	            if (q.length > 33) {
	                throw new RuntimeException("q length[ " + q.length + " ]");
	            } else {
	                System.arraycopy(q, 0, this.q, 32 - q.length, q.length);
	                if (g.length > 513) {
	                    throw new RuntimeException("g length[ " + g.length + " ]");
	                } else {
	                    System.arraycopy(g, 0, this.g, 512 - g.length, g.length);
	                    if (pubkey.length > 513) {
	                        throw new RuntimeException("pubkey length[ " + pubkey.length + " ]");
	                    } else {
	                        System.arraycopy(pubkey, 0, this.pubkey, 512 - pubkey.length, pubkey.length);
	                        if (privkey.length > 33) {
	                            throw new RuntimeException("privkey length[ " + privkey.length + " ]");
	                        } else {
	                            System.arraycopy(privkey, 0, this.privkey, 32 - privkey.length, privkey.length);
	                        }
	                    }
	                }
	            }
	        }
	    }

	    public int getBits() {
	        return this.bits;
	    }

	    public byte[] getP() {
	        return this.p;
	    }

	    public byte[] getQ() {
	        return this.q;
	    }

	    public byte[] getG() {
	        return this.g;
	    }

	    public byte[] getPrivkey() {
	        return this.privkey;
	    }

	    public byte[] getPubkey() {
	        return this.pubkey;
	    }

	    public void decode(byte[] bytes) throws CryptoException {
	        this.bits = BytesUtil.bytes2int(bytes);
	        int pos = 4;
	        System.arraycopy(bytes, pos, this.p, 0, 512);
	        pos = pos + this.p.length;
	        System.arraycopy(bytes, pos, this.q, 0, 32);
	        pos += this.q.length;
	        System.arraycopy(bytes, pos, this.g, 0, 512);
	        pos += this.g.length;
	        System.arraycopy(bytes, pos, this.pubkey, 0, 512);
	        pos += this.pubkey.length;
	        System.arraycopy(bytes, pos, this.privkey, 0, 32);
	        pos += this.privkey.length;
	        if (pos != bytes.length) {
	            throw new CryptoException("inputData length != DSArefPrivateKeyEx length");
	        }
	    }

	    public byte[] encode() throws CryptoException {
	        ByteArrayOutputStream buf = new ByteArrayOutputStream();

	        try {
	            buf.write(BytesUtil.int2bytes(this.bits));
	            buf.write(this.p);
	            buf.write(this.q);
	            buf.write(this.g);
	            buf.write(this.pubkey);
	            buf.write(this.privkey);
	        } catch (IOException var3) {
	            throw new CryptoException("DSArefPrivateKeyEx encode error.", var3);
	        }

	        return buf.toByteArray();
	    }

	    public String toString() {
	        return "DSArefPrivateKeyEx{bits=" + this.bits + ", p=" + BytesUtil.bytes2hex(this.p) + ", q=" + BytesUtil.bytes2hex(this.q) + ", g=" + BytesUtil.bytes2hex(this.g) + ", pubkey=" + BytesUtil.bytes2hex(this.pubkey) + ", privkey=" + BytesUtil.bytes2hex(this.privkey) + '}';
	    }

	    protected List getFieldOrder() {
	        return Arrays.asList("bits", "p", "q", "g", "pubkey", "privkey");
	    }

	    public static class ByValue extends DSArefPrivateKeyEx implements com.sun.jna.Structure.ByValue {
	        public ByValue() {
	        }
	    }

	    public static class ByReference extends DSArefPrivateKeyEx implements com.sun.jna.Structure.ByReference {
	        public ByReference() {
	        }
	    }
}
