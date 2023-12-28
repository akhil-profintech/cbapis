import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IAcceptedOffer } from '../accepted-offer.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../accepted-offer.test-samples';

import { AcceptedOfferService, RestAcceptedOffer } from './accepted-offer.service';

const requireRestSample: RestAcceptedOffer = {
  ...sampleWithRequiredData,
  offerDate: sampleWithRequiredData.offerDate?.format(DATE_FORMAT),
  offerAcceptedDate: sampleWithRequiredData.offerAcceptedDate?.format(DATE_FORMAT),
};

describe('AcceptedOffer Service', () => {
  let service: AcceptedOfferService;
  let httpMock: HttpTestingController;
  let expectedResult: IAcceptedOffer | IAcceptedOffer[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AcceptedOfferService);
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

    it('should create a AcceptedOffer', () => {
      const acceptedOffer = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(acceptedOffer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AcceptedOffer', () => {
      const acceptedOffer = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(acceptedOffer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AcceptedOffer', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AcceptedOffer', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AcceptedOffer', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAcceptedOfferToCollectionIfMissing', () => {
      it('should add a AcceptedOffer to an empty array', () => {
        const acceptedOffer: IAcceptedOffer = sampleWithRequiredData;
        expectedResult = service.addAcceptedOfferToCollectionIfMissing([], acceptedOffer);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(acceptedOffer);
      });

      it('should not add a AcceptedOffer to an array that contains it', () => {
        const acceptedOffer: IAcceptedOffer = sampleWithRequiredData;
        const acceptedOfferCollection: IAcceptedOffer[] = [
          {
            ...acceptedOffer,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAcceptedOfferToCollectionIfMissing(acceptedOfferCollection, acceptedOffer);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AcceptedOffer to an array that doesn't contain it", () => {
        const acceptedOffer: IAcceptedOffer = sampleWithRequiredData;
        const acceptedOfferCollection: IAcceptedOffer[] = [sampleWithPartialData];
        expectedResult = service.addAcceptedOfferToCollectionIfMissing(acceptedOfferCollection, acceptedOffer);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(acceptedOffer);
      });

      it('should add only unique AcceptedOffer to an array', () => {
        const acceptedOfferArray: IAcceptedOffer[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const acceptedOfferCollection: IAcceptedOffer[] = [sampleWithRequiredData];
        expectedResult = service.addAcceptedOfferToCollectionIfMissing(acceptedOfferCollection, ...acceptedOfferArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const acceptedOffer: IAcceptedOffer = sampleWithRequiredData;
        const acceptedOffer2: IAcceptedOffer = sampleWithPartialData;
        expectedResult = service.addAcceptedOfferToCollectionIfMissing([], acceptedOffer, acceptedOffer2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(acceptedOffer);
        expect(expectedResult).toContain(acceptedOffer2);
      });

      it('should accept null and undefined values', () => {
        const acceptedOffer: IAcceptedOffer = sampleWithRequiredData;
        expectedResult = service.addAcceptedOfferToCollectionIfMissing([], null, acceptedOffer, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(acceptedOffer);
      });

      it('should return initial array if no AcceptedOffer is added', () => {
        const acceptedOfferCollection: IAcceptedOffer[] = [sampleWithRequiredData];
        expectedResult = service.addAcceptedOfferToCollectionIfMissing(acceptedOfferCollection, undefined, null);
        expect(expectedResult).toEqual(acceptedOfferCollection);
      });
    });

    describe('compareAcceptedOffer', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAcceptedOffer(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareAcceptedOffer(entity1, entity2);
        const compareResult2 = service.compareAcceptedOffer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareAcceptedOffer(entity1, entity2);
        const compareResult2 = service.compareAcceptedOffer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareAcceptedOffer(entity1, entity2);
        const compareResult2 = service.compareAcceptedOffer(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
