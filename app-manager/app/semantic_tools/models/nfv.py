from .ngsi_ld.entity import Property, Relationship
from .common import Asset
from typing import Literal, Optional


class VNF(Asset):
    type: Literal["VNF"] = "VNF"
    descriptorId: Property
    hasMetrics: Optional[Relationship] = None
