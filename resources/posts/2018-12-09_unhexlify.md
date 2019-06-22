---
title: Python’s binascii – hexlify() and unhexlify()
authors: Alain M. Lafon
category: python
date-published: 2009-12-09
tags: 
- python
uuid: e219ff9f-81b9-4728-ac2a-b94e018b1b55
---

Some time ago, a dear friend of mine came up to me and asked about the
Python module binascii – particularly about the methods `hexlify()`
and `unhexlify()`. Since he asked for it, I’m going to share my answer
publicly with you.

First of all, I’m defining the used nomenclature:

    - ASCII characters are being written in single quotes
    - decimal numbers are of the type Long with a L suffix
    - hex values have a x prefix

First, let me quote the documentation:

> binascii.b2a_hex(data)
> binascii.hexlify(data)
>     Return the hexadecimal representation of the binary data. Every
>     byte of data is converted into the corresponding 2-digit hex
>     representation. The resulting string is therefore twice as long
>     as the length of data.

> binascii.a2b_hex(hexstr)
> binascii.unhexlify(hexstr)
>     Return the binary data represented by the hexadecimal string
>     hexstr. This function is the inverse of b2a_hex(). hexstr must
>     contain an even number of hexadecimal digits (which can be upper
>     or lower case), otherwise a TypeError is raised.

I’ll begin with `hexlify()`. As the documentation states, this method
splits a string which consists of hex-tuples into distinct bytes.

The ASCII character ‘A’ has 65L as numerical representation. To verify
this in Python:

```python
long(ord('A'))
65L
```

You might ask “Why is this even relevant to understand binascii?”
Well, we don’t know anything about how ord() does its job. But with
binascii we can re-calculate manually and verify.

```python
binascii.hexlify('A')
'41'
```

Now we know that an ‘A’ – interpreted as binary data and shown in hex
– resembles ’41’. But wait, ’41’ is a string and no hex value! That’s
no biggy, `hexlify()` represents its result as string.

To stay with the example, let’s convert 41 into a decimal number and
check if it equals 65L.

```python
long('41', 16)
65L
```

Tada! It seems that ‘A’ = 41 = 65L.
You might have known that already, but please, stay with me a minute
longer.

To make it look a little more complex:
	
```python
binascii.hexlify('A') == '%x' % long('41', 16)
True
```

Be aware that `'%x' % n` converts a decimal number `n` into its hex
representation.

<hr/>

`binascii.unhexlify()` naturally does the same thing as `hexlify()`,
but in reverse. It takes binary data and displays it in tuples of
hex-values.

I’ll start off with an example:
	
```python
binascii.unhexlify('41')
'A'
```
 
```python
binascii.unhexlify('%x' % ord('A'))
'A'
```

Here, `unhexlify()` takes the numerical representation 65L from the
ASCII character ‘A’
	
```python
ord('A')
65
```

converts it into hex 41
	
```python
'%x' % ord('A')
'41'
```

and represents it as a 1-tuple (meaning dimension of one) of hex
values.

And now the conclusio – why might all of this be useful? Right now, I
can think of at least four use cases:

 - cryptography
 - data-transformation (i.e. Base64 for MIME/E-Mail attachments)
 - security (deciphering binary readings off a network, pattern
   matching, …)
 - textual representation of escape sequences

Taking up the last example, I’ll show you how to visualize the Bell
escape sequence (you know, that thing that keeps beeping in your
terminal). Taken from the ASCII table, the numerical representation of
the Bell is 7. Programmers might know it better as `\a`.
	

```python
ord('\a') == 7
True
```

Presuming you read such a character in some kind of binary data – for
example from a socket and you want to visualize this data with
`print`, you will not get any results – at least none visible. You
might hear the Bell sound if you’re not on a silent terminal.

Now, finally – binascii to the rescue:
	
```python
binascii.hexlify('\a')
'07'
```

Voilà, the dubious string is decrypted.
