import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IPlacedOffer } from '../placed-offer.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../placed-offer.test-samples';

import { PlacedOfferService, RestPlacedOffer } from './placed-offer.service';

const requireRestSample: RestPlacedOffer = {
  ...sampleWithRequiredData,
  offerDate: sampleWithRequiredData.offerDate?.format(DATE_FORMAT),
  placedOfferDate: sampleWithRequiredData.placedOfferDate?.format(DATE_FORMAT),
};

describe('PlacedOffer Service', () => {
  let service: PlacedOfferService;
  let httpMock: HttpTestingController;
  let expectedResult: IPlacedOffer | IPlacedOffer[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PlacedOfferService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a PlacedOffer', () => {
      const placedOffer = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(placedOffer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a PlacedOffer', () => {
      const placedOffer = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(placedOffer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a PlacedOffer', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of PlacedOffer', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a PlacedOffer', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addPlacedOfferToCollectionIfMissing', () => {
      it('should add a PlacedOffer to an empty array', () => {
        const placedOffer: IPlacedOffer = sampleWithRequiredData;
        expectedResult = service.addPlacedOfferToCollectionIfMissing([], placedOffer);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(placedOffer);
      });

      it('should not add a PlacedOffer to an array that contains it', () => {
        const placedOffer: IPlacedOffer = sampleWithRequiredData;
        const placedOfferCollection: IPlacedOffer[] = [
          {
            ...placedOffer,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPlacedOfferToCollectionIfMissing(placedOfferCollection, placedOffer);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a PlacedOffer to an array that doesn't contain it", () => {
        const placedOffer: IPlacedOffer = sampleWithRequiredData;
        const placedOfferCollection: IPlacedOffer[] = [sampleWithPartialData];
        expectedResult = service.addPlacedOfferToCollectionIfMissing(placedOfferCollection, placedOffer);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(placedOffer);
      });

      it('should add only unique PlacedOffer to an array', () => {
        const placedOfferArray: IPlacedOffer[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const placedOfferCollection: IPlacedOffer[] = [sampleWithRequiredData];
        expectedResult = service.addPlacedOfferToCollectionIfMissing(placedOfferCollection, ...placedOfferArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const placedOffer: IPlacedOffer = sampleWithRequiredData;
        const placedOffer2: IPlacedOffer = sampleWithPartialData;
        expectedResult = service.addPlacedOfferToCollectionIfMissing([], placedOffer, placedOffer2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(placedOffer);
        expect(expectedResult).toContain(placedOffer2);
      });

      it('should accept null and undefined values', () => {
        const placedOffer: IPlacedOffer = sampleWithRequiredData;
        expectedResult = service.addPlacedOfferToCollectionIfMissing([], null, placedOffer, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(placedOffer);
      });

      it('should return initial array if no PlacedOffer is added', () => {
        const placedOfferCollection: IPlacedOffer[] = [sampleWithRequiredData];
        expectedResult = service.addPlacedOfferToCollectionIfMissing(placedOfferCollection, undefined, null);
        expect(expectedResult).toEqual(placedOfferCollection);
      });
    });

    describe('comparePlacedOffer', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePlacedOffer(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.comparePlacedOffer(entity1, entity2);
        const compareResult2 = service.comparePlacedOffer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.comparePlacedOffer(entity1, entity2);
        const compareResult2 = service.comparePlacedOffer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.comparePlacedOffer(entity1, entity2);
        const compareResult2 = service.comparePlacedOffer(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
