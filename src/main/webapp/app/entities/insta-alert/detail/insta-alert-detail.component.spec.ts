import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { InstaAlertDetailComponent } from './insta-alert-detail.component';

describe('InstaAlert Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstaAlertDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: InstaAlertDetailComponent,
              resolve: { instaAlert: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(InstaAlertDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load instaAlert on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', InstaAlertDetailComponent);

      // THEN
      expect(instance.instaAlert).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
