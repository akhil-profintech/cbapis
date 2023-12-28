import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IInstaAlert } from '../insta-alert.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../insta-alert.test-samples';

import { InstaAlertService } from './insta-alert.service';

const requireRestSample: IInstaAlert = {
  ...sampleWithRequiredData,
};

describe('InstaAlert Service', () => {
  let service: InstaAlertService;
  let httpMock: HttpTestingController;
  let expectedResult: IInstaAlert | IInstaAlert[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(InstaAlertService);
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

    it('should create a InstaAlert', () => {
      const instaAlert = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(instaAlert).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a InstaAlert', () => {
      const instaAlert = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(instaAlert).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a InstaAlert', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of InstaAlert', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a InstaAlert', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addInstaAlertToCollectionIfMissing', () => {
      it('should add a InstaAlert to an empty array', () => {
        const instaAlert: IInstaAlert = sampleWithRequiredData;
        expectedResult = service.addInstaAlertToCollectionIfMissing([], instaAlert);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(instaAlert);
      });

      it('should not add a InstaAlert to an array that contains it', () => {
        const instaAlert: IInstaAlert = sampleWithRequiredData;
        const instaAlertCollection: IInstaAlert[] = [
          {
            ...instaAlert,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addInstaAlertToCollectionIfMissing(instaAlertCollection, instaAlert);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a InstaAlert to an array that doesn't contain it", () => {
        const instaAlert: IInstaAlert = sampleWithRequiredData;
        const instaAlertCollection: IInstaAlert[] = [sampleWithPartialData];
        expectedResult = service.addInstaAlertToCollectionIfMissing(instaAlertCollection, instaAlert);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(instaAlert);
      });

      it('should add only unique InstaAlert to an array', () => {
        const instaAlertArray: IInstaAlert[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const instaAlertCollection: IInstaAlert[] = [sampleWithRequiredData];
        expectedResult = service.addInstaAlertToCollectionIfMissing(instaAlertCollection, ...instaAlertArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const instaAlert: IInstaAlert = sampleWithRequiredData;
        const instaAlert2: IInstaAlert = sampleWithPartialData;
        expectedResult = service.addInstaAlertToCollectionIfMissing([], instaAlert, instaAlert2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(instaAlert);
        expect(expectedResult).toContain(instaAlert2);
      });

      it('should accept null and undefined values', () => {
        const instaAlert: IInstaAlert = sampleWithRequiredData;
        expectedResult = service.addInstaAlertToCollectionIfMissing([], null, instaAlert, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(instaAlert);
      });

      it('should return initial array if no InstaAlert is added', () => {
        const instaAlertCollection: IInstaAlert[] = [sampleWithRequiredData];
        expectedResult = service.addInstaAlertToCollectionIfMissing(instaAlertCollection, undefined, null);
        expect(expectedResult).toEqual(instaAlertCollection);
      });
    });

    describe('compareInstaAlert', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareInstaAlert(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareInstaAlert(entity1, entity2);
        const compareResult2 = service.compareInstaAlert(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareInstaAlert(entity1, entity2);
        const compareResult2 = service.compareInstaAlert(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareInstaAlert(entity1, entity2);
        const compareResult2 = service.compareInstaAlert(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
