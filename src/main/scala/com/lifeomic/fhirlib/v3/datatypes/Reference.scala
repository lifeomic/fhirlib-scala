package com.lifeomic.fhirlib.v3.datatypes

import java.net.URI

class Reference(val reference: Option[URI],
                val identifier: Option[Identifier],
                val display: Option[String]) {
    def getId(): Option[String] = {
        try {
            if (!reference.get.getFragment().isEmpty) {
                return Some(reference.get.getFragment())
            }
            reference.get.getPath.split("/").lastOption
        } catch {
            case _ : Throwable => None
        }
    }
}
