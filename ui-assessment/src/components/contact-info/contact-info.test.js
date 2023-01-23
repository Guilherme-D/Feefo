import {render, screen} from '@testing-library/react';
import {ContactInfo} from './contact-info';

test('renders learn react link', () => {
  render(<ContactInfo />);
  const element = screen.getByText(/Build your components here/i);
  expect(element).toBeInTheDocument();
});
